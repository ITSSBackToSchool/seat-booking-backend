package org.itss.backtoschool.course.service;

import org.itss.backtoschool.course.dto.response.WeatherResponse;
import org.springframework.stereotype.Service;


public interface WeatherService {
    WeatherResponse getCurrentWeather();
}