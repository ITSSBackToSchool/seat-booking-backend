package org.itss.backtoschool.course.controller.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.controller.WeatherController;
import org.itss.backtoschool.course.dto.response.WeatherResponse;
import org.itss.backtoschool.course.service.WeatherService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weather")
public class WeatherControllerImpl implements WeatherController {

	private final WeatherService weatherService;
	@Override
	public WeatherResponse getCurrentWeather() {
		return weatherService.getCurrentWeather();
	}
}
