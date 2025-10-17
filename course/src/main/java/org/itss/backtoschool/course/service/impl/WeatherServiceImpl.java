package org.itss.backtoschool.course.service.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.dto.response.WeatherResponse;
import org.itss.backtoschool.course.service.WeatherService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public WeatherResponse getCurrentWeather() {
        return restTemplate.getForObject("https://api.open-meteo.com/v1/forecast?latitude=44.43&longitude=26.1063&daily=weather_code,temperature_2m_max,temperature_2m_min&current=temperature_2m,wind_speed_10m,weather_code,precipitation,rain,relative_humidity_2m,surface_pressure&timezone=auto"
                ,WeatherResponse.class);
    }
}
