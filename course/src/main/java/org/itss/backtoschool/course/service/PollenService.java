package org.itss.backtoschool.course.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PollenService {

    @Value("${google.maps.api-key:}")
    private String googleApiKey;

    public String getPollenForecast(double lat, double lon, int days) {
        String baseUrl = "https://pollen.googleapis.com/v1/forecast:lookup";

        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("location.latitude", lat)
                .queryParam("location.longitude", lon)
                .queryParam("days", days)
                .queryParam("key", googleApiKey)
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response.getBody();
    }
}


