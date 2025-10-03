package org.itss.backtoschool.course.controller;

import org.itss.backtoschool.course.dto.response.WeatherResponse;
import org.springframework.web.bind.annotation.GetMapping;
public interface WeatherController {

    @GetMapping
    public WeatherResponse getCurrentWeather();
}