package org.itss.backtoschool.deskops.controller;

import org.itss.backtoschool.deskops.dto.WeatherDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

public interface WeatherController {

    @GetMapping("/building/{buildingId}")
    ResponseEntity<WeatherDTO> getWeatherForDate(
            @PathVariable Long buildingId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    );

    @GetMapping("/building/{buildingId}/forecast")
    ResponseEntity<List<WeatherDTO>> getWeatherForecast(
            @PathVariable Long buildingId,
            @RequestParam(required = false, defaultValue = "5") Integer days
    );
}