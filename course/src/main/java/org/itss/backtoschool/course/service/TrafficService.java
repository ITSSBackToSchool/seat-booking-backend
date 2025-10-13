package org.itss.backtoschool.course.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.dto.response.TrafficResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class TrafficService {

    private static final String API_KEY = "AIzaSyCkoMpJxoH8tiZiKdLFqzLSFaxkL4f9Ifs";

    public TrafficResponse getTrafficInfo(String origin, String destination) throws Exception {
        // Encode addresses or coordinates
        String encodedOrigin = URLEncoder.encode(origin, StandardCharsets.UTF_8.toString());
        String encodedDestination = URLEncoder.encode(destination, StandardCharsets.UTF_8.toString());

        String url = String.format(
                "https://maps.googleapis.com/maps/api/directions/json?origin=%s&destination=%s&departure_time=now&traffic_model=best_guess&key=%s",
                encodedOrigin, encodedDestination, API_KEY
        );

        RestTemplate restTemplate = new RestTemplate();
        String responseBody = restTemplate.getForObject(url, String.class);

        // Log Google API response for debugging
        System.out.println("Google API response: " + responseBody);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseBody);

        if (!root.path("status").asText().equals("OK")) {
            throw new RuntimeException("Google API error: " + root.path("status").asText() +
                    " | Message: " + root.path("error_message").asText(""));
        }

        JsonNode leg = root.path("routes").get(0).path("legs").get(0);

        double distanceKm = leg.path("distance").path("value").asDouble() / 1000.0;
        double normalDurationMin = leg.path("duration").path("value").asDouble() / 60.0;
        double trafficDurationMin = leg.path("duration_in_traffic").path("value").asDouble() / 60.0;
        double delayMinutes = trafficDurationMin - normalDurationMin;

        String startAddress = leg.path("start_address").asText();
        String endAddress = leg.path("end_address").asText();

        boolean isTraffic = delayMinutes > 1.0;

        String trafficLevel;
        if (delayMinutes < 2) {
            trafficLevel = "NO_TRAFFIC";
        } else if (delayMinutes < 5) {
            trafficLevel = "LIGHT";
        } else if (delayMinutes < 10) {
            trafficLevel = "MEDIUM";
        } else {
            trafficLevel = "HEAVY";
        }

        return TrafficResponse.builder()
                .start(startAddress)
                .end(endAddress)
                .distanceKm(Math.round(distanceKm * 100.0) / 100.0)
                .normalDurationMin(Math.round(normalDurationMin))
                .trafficDurationMin(Math.round(trafficDurationMin))
                .trafficDelayMin(Math.round(delayMinutes))
                .isTraffic(isTraffic)
                .trafficLevel(trafficLevel)
                .build();
    }
}
