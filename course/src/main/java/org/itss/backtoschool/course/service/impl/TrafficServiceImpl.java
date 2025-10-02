package org.itss.backtoschool.course.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.itss.backtoschool.course.dto.TrafficCoordinate;
import org.itss.backtoschool.course.dto.TrafficIncident;
import org.itss.backtoschool.course.dto.TrafficRouteOption;
import org.itss.backtoschool.course.dto.request.TrafficRouteRequest;
import org.itss.backtoschool.course.dto.response.TrafficRouteResponse;
import org.itss.backtoschool.course.service.TrafficService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrafficServiceImpl implements TrafficService {

    private final WebClient.Builder webClientBuilder;

    @Value("${azure.maps.subscription-key}")
    private String subscriptionKey;

    @Value("${azure.maps.base-url}")
    private String baseUrl;

    @Value("${azure.maps.api-version}")
    private String apiVersion;

    @Value("${company.headquarters.latitude}")
    private Double hqLatitude;

    @Value("${company.headquarters.longitude}")
    private Double hqLongitude;

    @Override
    public TrafficRouteResponse getRoutesToHeadquarters(TrafficRouteRequest request) {
        log.info("Fetching routes from {} to headquarters", request.getOriginAddress());

        // Build origin and destination coordinates
        String origin = buildCoordinates(request.getOriginLatitude(), request.getOriginLongitude());
        String destination = buildCoordinates(hqLatitude, hqLongitude);

        // Default values
        int maxAlternatives = request.getMaxAlternatives() != null ? request.getMaxAlternatives() : 3;
        boolean includeTraffic = request.getIncludeTraffic() != null ? request.getIncludeTraffic() : true;

        // Fetch routes from Azure Maps
        List<TrafficRouteOption> routes = fetchRoutes(origin, destination, maxAlternatives, includeTraffic);

        // Fetch traffic incidents
        List<TrafficIncident> incidents = fetchTrafficIncidents(
                request.getOriginLatitude(), request.getOriginLongitude(),
                hqLatitude, hqLongitude
        );

        // Generate map image URL
        String mapImageUrl = generateMapImageUrl(origin, destination);

        return TrafficRouteResponse.builder()
                .routes(routes)
                .trafficIncidents(incidents)
                .mapImageUrl(mapImageUrl)
                .build();
    }

    private List<TrafficRouteOption> fetchRoutes(String origin, String destination,
                                                 int maxAlternatives, boolean includeTraffic) {
        WebClient webClient = webClientBuilder.baseUrl(baseUrl).build();

        String uri = String.format(
                "/route/directions/json?api-version=%s&query=%s:%s&maxAlternatives=%d&traffic=%s&instructionsType=text&subscription-key=%s",
                apiVersion, origin, destination, maxAlternatives, includeTraffic, subscriptionKey
        );

        log.debug("Calling Azure Maps API: {}", baseUrl + uri.replace(subscriptionKey, "***"));

        try {
            AzureMapsRouteResponse response = webClient.get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(AzureMapsRouteResponse.class)
                    .block();

            if (response != null && response.getRoutes() != null) {
                return IntStream.range(0, response.getRoutes().size())
                        .mapToObj(i -> mapToRouteOption(response.getRoutes().get(i), i))
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            log.error("Error fetching routes from Azure Maps", e);
        }

        return new ArrayList<>();
    }

    private List<TrafficIncident> fetchTrafficIncidents(Double originLat, Double originLon,
                                                        Double destLat, Double destLon) {
        WebClient webClient = webClientBuilder.baseUrl(baseUrl).build();

        // Calculate bounding box
        String boundingBox = String.format("%f,%f,%f,%f",
                Math.min(originLon, destLon) - 0.05,
                Math.min(originLat, destLat) - 0.05,
                Math.max(originLon, destLon) + 0.05,
                Math.max(originLat, destLat) + 0.05
        );

        String uri = String.format(
                "/traffic/incident/detail/json?api-version=%s&bbox=%s&subscription-key=%s",
                apiVersion, boundingBox, subscriptionKey
        );

        log.debug("Fetching traffic incidents with bounding box: {}", boundingBox);

        try {
            AzureTrafficIncidentResponse response = webClient.get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(AzureTrafficIncidentResponse.class)
                    .block();

            if (response != null && response.getIncidents() != null) {
                return response.getIncidents().stream()
                        .map(this::mapToTrafficIncident)
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            log.error("Error fetching traffic incidents", e);
        }

        return new ArrayList<>();
    }

    private String generateMapImageUrl(String origin, String destination) {
        // Generate static map image URL with route markers
        return String.format(
                "%s/map/static/png?api-version=%s&zoom=11&center=%s&pins=default|coFF0000|la1||%s|default|coGreen|la2||%s&subscription-key=%s",
                baseUrl, apiVersion, origin, origin, destination, subscriptionKey
        );
    }

    private String buildCoordinates(Double lat, Double lon) {
        return String.format("%f,%f", lat, lon);
    }

    private TrafficRouteOption mapToRouteOption(AzureMapsRoute azureRoute, int index) {
        RouteSummary summary = azureRoute.getSummary();

        return TrafficRouteOption.builder()
                .routeName(index == 0 ? "Best Route" : "Alternative " + index)
                .durationInSeconds(summary.getTravelTimeInSeconds())
                .distanceInMeters(summary.getLengthInMeters())
                .delayInSeconds(summary.getTrafficDelayInSeconds() != null ? summary.getTrafficDelayInSeconds() : 0)
                .durationDisplay(formatDuration(summary.getTravelTimeInSeconds()))
                .distanceDisplay(formatDistance(summary.getLengthInMeters()))
                .summary(String.format("%s, %s", formatDistance(summary.getLengthInMeters()), formatDuration(summary.getTravelTimeInSeconds())))
                .isBestRoute(index == 0)
                .geometry(extractGeometry(azureRoute))
                .build();
    }

    private TrafficIncident mapToTrafficIncident(AzureIncident incident) {
        return TrafficIncident.builder()
                .type(incident.getType())
                .description(incident.getDescription())
                .location(new TrafficCoordinate(incident.getPoint().getLatitude(), incident.getPoint().getLongitude()))
                .delayInSeconds(incident.getDelay() != null ? incident.getDelay() : 0)
                .severity(incident.getMagnitude() != null ? String.valueOf(incident.getMagnitude()) : "UNKNOWN")
                .build();
    }

    private List<TrafficCoordinate> extractGeometry(AzureMapsRoute route) {
        List<TrafficCoordinate> coordinates = new ArrayList<>();

        if (route.getLegs() != null) {
            for (RouteLeg leg : route.getLegs()) {
                if (leg.getPoints() != null) {
                    for (RoutePoint point : leg.getPoints()) {
                        coordinates.add(new TrafficCoordinate(point.getLatitude(), point.getLongitude()));
                    }
                }
            }
        }

        return coordinates;
    }

    private String formatDuration(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;

        if (hours > 0) {
            return String.format("%d hr %d min", hours, minutes);
        }
        return String.format("%d min", minutes);
    }

    private String formatDistance(int meters) {
        double km = meters / 1000.0;
        return String.format("%.1f km", km);
    }

    // Inner classes for Azure Maps API response mapping
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class AzureMapsRouteResponse {
        private List<AzureMapsRoute> routes;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class AzureMapsRoute {
        private RouteSummary summary;
        private List<RouteLeg> legs;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class RouteSummary {
        private Integer lengthInMeters;
        private Integer travelTimeInSeconds;
        private Integer trafficDelayInSeconds;
        private String departureTime;
        private String arrivalTime;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class RouteLeg {
        private List<RoutePoint> points;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class RoutePoint {
        private Double latitude;
        private Double longitude;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class AzureTrafficIncidentResponse {
        private List<AzureIncident> incidents;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class AzureIncident {
        @JsonProperty("ty")
        private String type;

        @JsonProperty("d")
        private String description;

        @JsonProperty("p")
        private IncidentPoint point;

        @JsonProperty("dl")
        private Integer delay;

        @JsonProperty("m")
        private Integer magnitude;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class IncidentPoint {
        @JsonProperty("la")
        private Double latitude;

        @JsonProperty("lg")
        private Double longitude;
    }
}
