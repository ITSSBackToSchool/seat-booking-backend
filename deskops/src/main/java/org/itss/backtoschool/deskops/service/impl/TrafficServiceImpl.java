package org.itss.backtoschool.deskops.service.impl;

import org.itss.backtoschool.deskops.dto.request.GetTrafficRequest;
import org.itss.backtoschool.deskops.dto.response.GetTrafficResponse;
import org.itss.backtoschool.deskops.service.TrafficService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

public class TrafficServiceImpl implements TrafficService {

    private static final String GEOCODE_URL = "https://api.tomtom.com/search/2/geocode/{address}.json";
    private static final String ROUTE_URL = "https://api.tomtom.com/routing/1/calculateRoute/{coordinates}/json";
    private static final String TRAFFIC_FLOW_URL = "https://api.tomtom.com/traffic/services/4/flowSegmentData/absolute/10/json";
    private static final String TRAFFIC_INCIDENTS_URL = "https://api.tomtom.com/traffic/services/5/incidentDetails";

    private static final double OFFICE_LAT = 44.4268;
    private static final double OFFICE_LON = 26.1025;

    // Decision thresholds
    private static final int MAX_ROUTE_TIME_MINUTES = 45;
    private static final double MIN_TRAFFIC_SPEED_KMH = 20.0;
    private static final int MAX_INCIDENTS_COUNT = 3;

    @Value("${tomtom.api.key}")
    private String apiKey;

    private final WebClient webClient;

    public TrafficServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://api.tomtom.com")
                .build();
    }

    @Override
    public GetTrafficResponse getTrafficRecommendation(GetTrafficRequest request) {
        return null;
    }
}
