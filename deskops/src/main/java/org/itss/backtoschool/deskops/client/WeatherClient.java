package org.itss.backtoschool.deskops.client;

import org.itss.backtoschool.deskops.dto.external.weather.OpenWeatherMapResponse;

import java.time.LocalDate;

public interface WeatherClient {
    OpenWeatherMapResponse fetchForecast(Double latitude, Double longitude, Integer days);
}