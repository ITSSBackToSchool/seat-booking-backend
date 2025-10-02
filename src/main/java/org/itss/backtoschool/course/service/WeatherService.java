package org.itss.backtoschool.course.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private static final String API_KEY = "";

    public String getWeatherForDate(String city, String date) throws Exception {
        String url = String.format(
                "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/%s/%s?unitGroup=metric&key=%s&contentType=json",
                city, date, API_KEY
        );

        RestTemplate restTemplate = new RestTemplate();
        String responseBody = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseBody);


        JsonNode day = root.path("days").get(0);

        String conditions = day.path("conditions").asText();
        double tempMax = day.path("tempmax").asDouble();
        double tempMin = day.path("tempmin").asDouble();

        return String.format(
                "Prognoza pentru %s (%s): %.1f°C max, %.1f°C min, %s",
                city, date, tempMax, tempMin, conditions
        );
    }
}
