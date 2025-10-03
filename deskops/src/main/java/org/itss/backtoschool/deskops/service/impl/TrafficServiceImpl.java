package org.itss.backtoschool.deskops.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.itss.backtoschool.deskops.dto.TrafficDTO;
import org.itss.backtoschool.deskops.dto.request.GetTrafficRequest;
import org.itss.backtoschool.deskops.dto.response.*;
import org.itss.backtoschool.deskops.entities.User;
import org.itss.backtoschool.deskops.exception.traffic.GeocodeException;
import org.itss.backtoschool.deskops.exception.traffic.HomeAddressNotConfiguredException;
import org.itss.backtoschool.deskops.exception.traffic.RouteCalculationException;
import org.itss.backtoschool.deskops.exception.traffic.TrafficApiException;
import org.itss.backtoschool.deskops.models.Coordinates;
import org.itss.backtoschool.deskops.models.RouteInfo;
import org.itss.backtoschool.deskops.models.TrafficFlowInfo;
import org.itss.backtoschool.deskops.models.TrafficIncidentInfo;
import org.itss.backtoschool.deskops.repository.LocationRepository;
import org.itss.backtoschool.deskops.service.TrafficService;
import org.itss.backtoschool.deskops.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
@Service
public class TrafficServiceImpl implements TrafficService {

    private static final ZoneId BUCHAREST_TIMEZONE = ZoneId.of("Europe/Bucharest");

    private static final String GEOCODE_URL = "https://api.tomtom.com/search/2/geocode/{address}.json";
    private static final String ROUTE_URL = "https://api.tomtom.com/routing/1/calculateRoute/{coordinates}/json";
    private static final String TRAFFIC_FLOW_URL = "https://api.tomtom.com/traffic/services/4/flowSegmentData/absolute/10/json";
    private static final String TRAFFIC_INCIDENTS_URL = "https://api.tomtom.com/traffic/services/5/incidentDetails";

    private static final int MAX_ROUTE_TIME_MINUTES = 45;
    private static final double MIN_TRAFFIC_SPEED_KMH = 20.0;
    private static final int MAX_INCIDENTS_COUNT = 3;

    @Value("${traffic.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final LocationRepository locationRepository;
    private final UserService userService;

    public TrafficServiceImpl(RestTemplate restTemplate, LocationRepository locationRepository, UserService userService) {
        this.restTemplate = restTemplate;
        this.locationRepository = locationRepository;
        this.userService = userService;
    }

    @Override
    public GetTrafficResponse getTrafficRecommendation(Long buildingId, Jwt jwt) {
        // Get user from JWT
        User user = userService.getOrCreateUser(jwt);

        // Check if user has configured home address
        if (user.getHomeStreet() == null || user.getHomeStreetNumber() == null || user.getHomeCity() == null) {
            throw new HomeAddressNotConfiguredException("User has not configured their home address");
        }

        log.debug("Fetching traffic recommendation for user {} to building {} with home address: {}, {}, {}",
                user.getId(), buildingId, user.getHomeStreet(), user.getHomeStreetNumber(), user.getHomeCity());

        TrafficDTO trafficData = calculateTrafficData(user, buildingId);

        return GetTrafficResponse.builder()
                .travelTimeMinutes(trafficData.getTravelTimeMinutes())
                .delayMinutes(trafficData.getDelayMinutes())
                .workFromHomeRecommendation(trafficData.getWorkFromHomeRecommendation())
                .fetchedAt(trafficData.getFetchedAt())
                .build();
    }

    @Override
    @Cacheable(value = "trafficCache", key = "#user.id + '_' + #buildingId")
    public TrafficDTO getTrafficForUser(User user, Long buildingId) {
        if (user.getHomeStreet() == null || user.getHomeStreetNumber() == null || user.getHomeCity() == null) {
            throw new HomeAddressNotConfiguredException("User has not configured their home address");
        }

        log.debug("Fetching traffic for user {} to building {} with home address: {}, {}, {}",
                user.getId(), buildingId, user.getHomeStreet(), user.getHomeStreetNumber(), user.getHomeCity());

        return calculateTrafficData(user, buildingId);
    }

    private TrafficDTO calculateTrafficData(User user, Long buildingId) {
        // Get building location
        var location = locationRepository.findByBuildingId(buildingId)
                .orElseThrow(() -> new org.itss.backtoschool.deskops.exception.weather.LocationNotConfiguredException(
                        "Location not configured for building ID: " + buildingId));

        Coordinates homeCoordinates = getCoordinates(user.getHomeStreet(), user.getHomeStreetNumber(), user.getHomeCity());
        Coordinates officeCoordinates = new Coordinates(location.getLatitude(), location.getLongitude());
        RouteInfo routeInfo = calculateRoute(homeCoordinates, officeCoordinates);
        TrafficFlowInfo trafficFlow = getTrafficFlow(homeCoordinates);
        TrafficIncidentInfo incidents = getTrafficIncidents(homeCoordinates);

        boolean shouldWorkFromHome = makeDecision(routeInfo, trafficFlow, incidents);
        int travelTimeMinutes = routeInfo.getTravelTimeSeconds() / 60;
        int delayMinutes = routeInfo.getTrafficDelaySeconds() / 60;

        return TrafficDTO.builder()
                .travelTimeMinutes(travelTimeMinutes)
                .delayMinutes(delayMinutes)
                .workFromHomeRecommendation(shouldWorkFromHome)
                .fetchedAt(ZonedDateTime.now(BUCHAREST_TIMEZONE).toLocalDateTime())
                .build();
    }

    private Coordinates getCoordinates(String street, int streetNumber, String city) {
        try {
            String address = String.format("%s %d, %s", street, streetNumber, city);

            log.debug("Geocoding address: {}", address);

            GeocodeAPIResponse response = restTemplate.getForObject(
                    GEOCODE_URL + "?key={apiKey}",
                    GeocodeAPIResponse.class,
                    address,
                    apiKey
            );

            if (response != null && response.getResults() != null && !response.getResults().isEmpty()) {
                GeocodeResult result = response.getResults().getFirst();
                return new Coordinates(
                        result.getPosition().getLat(),
                        result.getPosition().getLon()
                );
            }

            throw new GeocodeException("No results found for address: " + address);
        } catch (GeocodeException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to geocode address: {}", e.getMessage(), e);
            throw new GeocodeException("Failed to geocode address", e);
        }
    }

    private RouteInfo calculateRoute(Coordinates homeCoordinates, Coordinates officeCoordinates) {
        try {
            String coordinates = String.format("%f,%f:%f,%f",
                    homeCoordinates.getLat(), homeCoordinates.getLon(),
                    officeCoordinates.getLat(), officeCoordinates.getLon());

            log.debug("Calculating route for coordinates: {}", coordinates);

            RouteResponse response = restTemplate.getForObject(
                    ROUTE_URL + "?traffic={traffic}&routeType={routeType}&key={apiKey}",
                    RouteResponse.class,
                    coordinates,
                    "true",
                    "fastest",
                    apiKey
            );

            if (response != null && response.getRoutes() != null && !response.getRoutes().isEmpty()) {
                Route route = response.getRoutes().getFirst();
                Summary summary = route.getSummary();

                return RouteInfo.builder()
                        .travelTimeSeconds(summary.getTravelTimeInSeconds())
                        .trafficDelaySeconds(summary.getTrafficDelayInSeconds())
                        .lengthMeters(summary.getLengthInMeters())
                        .build();
            }

            throw new RouteCalculationException("No route found");
        } catch (RouteCalculationException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to calculate route: {}", e.getMessage(), e);
            throw new RouteCalculationException("Failed to calculate route", e);
        }
    }

    private TrafficFlowInfo getTrafficFlow(Coordinates coordinates) {
        try {
            String point = String.format("%f,%f", coordinates.getLat(), coordinates.getLon());

            log.debug("Fetching traffic flow for point: {}", point);

            TrafficFlowResponse response = restTemplate.getForObject(
                    TRAFFIC_FLOW_URL + "?key={apiKey}&point={point}",
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

            throw new TrafficApiException("No traffic flow data found");
        } catch (TrafficApiException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to fetch traffic flow: {}", e.getMessage(), e);
            throw new TrafficApiException("Failed to fetch traffic flow data", e);
        }
    }

    private TrafficIncidentInfo getTrafficIncidents(Coordinates coordinates) {
        try {
            // Create bounding box around the route (approximately 0.01 degrees ~ 1km)
            double bbox = 0.005;
            String bboxParam = String.format("%f,%f,%f,%f",
                    coordinates.getLon() - bbox, coordinates.getLat() - bbox,
                    coordinates.getLon() + bbox, coordinates.getLat() + bbox);

            String fields = "{incidents{type,geometry{type,coordinates},properties{iconCategory}}}";

            log.debug("Fetching traffic incidents for bbox: {}", bboxParam);

            TrafficIncidentResponse response = restTemplate.getForObject(
                    TRAFFIC_INCIDENTS_URL + "?key={apiKey}&bbox={bbox}&fields={fields}&language={language}&timeValidityFilter={timeFilter}",
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

            throw new TrafficApiException("No traffic incident data found");
        } catch (TrafficApiException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to fetch traffic incidents: {}", e.getMessage(), e);
            throw new TrafficApiException("Failed to fetch traffic incidents", e);
        }
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
        return delayRatio > 0.5;
    }
}
