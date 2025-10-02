package org.itss.backtoschool.deskops.service;

import org.itss.backtoschool.deskops.dto.WeatherDTO;

import java.time.LocalDate;
import java.util.List;

public interface WeatherService {
    WeatherDTO getWeatherForDate(Long buildingId, LocalDate date);

    List<WeatherDTO> getWeatherForecast(Long buildingId, Integer days);
}