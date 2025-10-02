package org.itss.backtoschool.deskops.service.impl;

import org.itss.backtoschool.deskops.dto.request.GetTrafficRequest;
import org.itss.backtoschool.deskops.dto.response.*;
import org.itss.backtoschool.deskops.models.Coordinates;
import org.itss.backtoschool.deskops.models.RouteInfo;
import org.itss.backtoschool.deskops.models.TrafficFlowInfo;
import org.itss.backtoschool.deskops.models.TrafficIncidentInfo;
import org.itss.backtoschool.deskops.service.TrafficService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TrafficServiceImpl implements TrafficService {

    private static final String GEOCODE_URL = "https://api.tomtom.com/search/2/geocode/{address}.json";
    private static final String ROUTE_URL = "https://api.tomtom.com/routing/1/calculateRoute/{coordinates}/json";
    private static final String TRAFFIC_FLOW_URL = "https://api.tomtom.com/traffic/services/4/flowSegmentData/absolute/10/json";
    private static final String TRAFFIC_INCIDENTS_URL = "https://api.tomtom.com/traffic/services/5/incidentDetails";

    private static final double OFFICE_LAT = 44.4268;
    private static final double OFFICE_LON = 26.1025;

    private static final int MAX_ROUTE_TIME_MINUTES = 45;
    private static final double MIN_TRAFFIC_SPEED_KMH = 20.0;
    private static final int MAX_INCIDENTS_COUNT = 3;

    @Value("${traffic.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public TrafficServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public GetTrafficResponse getTrafficRecommendation(String street, int streetNumber, String city) {

        try {
            Coordinates homeCoordinates = getCoordinates(street, streetNumber, city);

            if (homeCoordinates == null) {
                return GetTrafficResponse.builder().WorkFromHome(true).build();
            }

            RouteInfo routeInfo = calculateRoute(homeCoordinates);

            TrafficFlowInfo trafficFlow = getTrafficFlow(homeCoordinates);

            TrafficIncidentInfo incidents = getTrafficIncidents(homeCoordinates);

            boolean shouldWorkFromHome = makeDecision(routeInfo, trafficFlow, incidents);
            return GetTrafficResponse.builder().WorkFromHome(shouldWorkFromHome).build();

        } catch (Exception e) {
            return GetTrafficResponse.builder().WorkFromHome(true).build();
        }
    }

    private Coordinates getCoordinates(String street, int streetNumber, String city)
    {
       try
       {
           String address = String.format("%s %d, %s",
                   street, streetNumber,city);

           GeocodeAPIResponse response = restTemplate.getForObject(
                   GEOCODE_URL,
                   GeocodeAPIResponse.class,
                   address,
                   apiKey
           );

           if (response != null && response.getResults() != null && !response.getResults().isEmpty()) {
               GeocodeResult result = response.getResults().get(0);
               return new Coordinates(
                       result.getPosition().getLat(),
                       result.getPosition().getLon()
               );
           }
       }
       catch(Exception e)
       {

       }
       return null;
    }

    private RouteInfo calculateRoute(Coordinates homeCoordinates)
    {
        try
        {
            String coordinates = String.format("%f,%f:%f,%f",
                    homeCoordinates.getLat(), homeCoordinates.getLon(),
                    OFFICE_LAT, OFFICE_LON);

            RouteResponse response = restTemplate.getForObject(
                    ROUTE_URL,
                    RouteResponse.class,
                    coordinates,
                    "true",
                    "fastest",
                    apiKey
            );

            if (response != null && response.getRoutes() != null && !response.getRoutes().isEmpty()) {
                Route route = response.getRoutes().get(0);
                Summary summary = route.getSummary();

                return RouteInfo.builder()
                        .travelTimeSeconds(summary.getTravelTimeInSeconds())
                        .trafficDelaySeconds(summary.getTrafficDelayInSeconds())
                        .lengthMeters(summary.getLengthInMeters())
                        .build();
            }
        }
        catch(Exception e)
        {

        }
        return null;
    }

    private TrafficFlowInfo getTrafficFlow(Coordinates coordinates) {
        try {
            String point = String.format("%f,%f", coordinates.getLat(), coordinates.getLon());

            TrafficFlowResponse response = restTemplate.getForObject(
                    TRAFFIC_FLOW_URL,
                    TrafficFlowResponse.class,
                    apiKey,
                    point
            );

            if (response != null && response.getFlowSegmentData() != null) {
                FlowSegmentData data = response.getFlowSegmentData();
                return TrafficFlowInfo.builder()
                        .currentSpeed(data.getCurrentSpeed())
                        .freeFlowSpeed(data.getFreeFlowSpeed())
                        .currentTravelTime(data.getCurrentTravelTime())
                        .freeFlowTravelTime(data.getFreeFlowTravelTime())
                        .build();
            }
        } catch (Exception e) {

        }
        return TrafficFlowInfo.builder().currentSpeed(50.0).freeFlowSpeed(50.0).build();
    }

    private TrafficIncidentInfo getTrafficIncidents(Coordinates coordinates) {
        try {
            // Create bounding box around the route (approximately 0.01 degrees ~ 1km)
            double bbox = 0.005;
            String bboxParam = String.format("%f,%f,%f,%f",
                    coordinates.getLon() - bbox, coordinates.getLat() - bbox,
                    coordinates.getLon() + bbox, coordinates.getLat() + bbox);

            String fields = "{incidents{type,geometry{type,coordinates},properties{iconCategory}}}";

            TrafficIncidentResponse response = restTemplate.getForObject(
                    TRAFFIC_INCIDENTS_URL,
                    TrafficIncidentResponse.class,
                    apiKey,
                    bboxParam,
                    fields,
                    "en-GB",
                    "present"
            );

            if (response != null && response.getIncidents() != null) {
                int severeIncidents = (int) response.getIncidents().stream()
                        .filter(incident -> isSevereIncident(incident.getProperties().getIconCategory()))
                        .count();

                return TrafficIncidentInfo.builder()
                        .totalIncidents(response.getIncidents().size())
                        .severeIncidents(severeIncidents)
                        .build();
            }
        } catch (Exception e) {

        }
        return TrafficIncidentInfo.builder().totalIncidents(0).severeIncidents(0).build();
    }

    private boolean isSevereIncident(int iconCategory)
    {
        return iconCategory >= 1 && iconCategory <= 4;
    }

    private boolean makeDecision(RouteInfo routeInfo, TrafficFlowInfo trafficFlow,
                                 TrafficIncidentInfo incidents) {

        int travelTimeMinutes = routeInfo.getTravelTimeSeconds() / 60;
        int delayMinutes = routeInfo.getTrafficDelaySeconds() / 60;

        // Recommend work from home if:
        // 1. Travel time exceeds threshold
        if (travelTimeMinutes > MAX_ROUTE_TIME_MINUTES) {
            return true;
        }

        // 2. Traffic speed is too slow
        if (trafficFlow.getCurrentSpeed() < MIN_TRAFFIC_SPEED_KMH) {
            return true;
        }

        // 3. Too many incidents on the route
        if (incidents.getTotalIncidents() > MAX_INCIDENTS_COUNT) {
            return true;
        }

        // 4. Any severe incidents
        if (incidents.getSevereIncidents() > 0) {
            return true;
        }

        // 5. Traffic delay is more than 50% of normal time
        double delayRatio = routeInfo.getTrafficDelaySeconds() /
                (double) (routeInfo.getTravelTimeSeconds() - routeInfo.getTrafficDelaySeconds());
        if (delayRatio > 0.5) {
            return true;
        }

        return false;
    }
}
