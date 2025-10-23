package org.itss.backtoschool.course.controller;

import org.itss.backtoschool.course.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/api/weather")
    public ResponseEntity<?> getWeather(
            @RequestParam String city,
            @RequestParam String date
    ) {
        try {
            String result = weatherService.getWeatherForDate(city, date);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Eroare: " + e.getMessage());
        }
    }
}


