package org.itss.backtoschool.deskops.controller.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.deskops.controller.WeatherController;
import org.itss.backtoschool.deskops.dto.WeatherDTO;
import org.itss.backtoschool.deskops.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherControllerImpl implements WeatherController {

    private final WeatherService weatherService;

    @Override
    public ResponseEntity<WeatherDTO> getWeatherForDate(Long buildingId, LocalDate date) {
        WeatherDTO weather = weatherService.getWeatherForDate(buildingId, date);
        return ResponseEntity.ok(weather);
    }

    @Override
    public ResponseEntity<List<WeatherDTO>> getWeatherForecast(Long buildingId, Integer days) {
        List<WeatherDTO> forecast = weatherService.getWeatherForecast(buildingId, days);
        return ResponseEntity.ok(forecast);
    }
}