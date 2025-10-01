package org.itss.backtoschool.course.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class WeatherService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiKey = "38b299938d8652bc02580415c1c5f8f9";

    public Map<String, Object> getWeather(String city) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";
        return restTemplate.getForObject(url, Map.class);
    }
}
