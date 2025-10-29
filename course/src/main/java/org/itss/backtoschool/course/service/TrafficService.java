package org.itss.backtoschool.course.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.dto.response.TrafficResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class TrafficService {

    @Value("${google.maps.api-key}")
    private String apiKey;

    public TrafficResponse getTrafficInfo(String origin, String destination) throws Exception {
        return getTrafficInfo(origin, destination, null);
    }

    public TrafficResponse getTrafficInfo(String origin, String destination, LocalDateTime departureTime) throws Exception {
        if (apiKey == null || apiKey.isEmpty() || apiKey.equals("YOUR_GOOGLE_MAPS_API_KEY_HERE")) {
            throw new RuntimeException("Google Maps API key is not configured. Please add your API key to application.yml");
        }

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("https://maps.googleapis.com/maps/api/directions/json")
                .queryParam("origin", origin)
                .queryParam("destination", destination)
                .queryParam("traffic_model", "best_guess")
                .queryParam("key", apiKey);

        if (departureTime != null) {
            long unixTimestamp = departureTime.toEpochSecond(ZoneOffset.UTC);
            System.out.println("Departure time: " + departureTime);
            System.out.println("Unix timestamp in seconds: " + unixTimestamp);
            builder.queryParam("departure_time", unixTimestamp);
        } else {
            builder.queryParam("departure_time", "now");
        }

        String url = builder.toUriString();
        System.out.println("Full URL: " + url);

        RestTemplate restTemplate = new RestTemplate();
        String responseBody = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseBody);

        String apiStatus = root.path("status").asText();
        System.out.println("Google API Status: " + apiStatus);
        
        if (!apiStatus.equals("OK")) {
            String errorMsg = root.path("error_message").asText("");
            System.out.println("Google API Error: " + errorMsg);
            throw new RuntimeException("Error Google API: " + apiStatus +
                    " | Message: " + errorMsg);
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
