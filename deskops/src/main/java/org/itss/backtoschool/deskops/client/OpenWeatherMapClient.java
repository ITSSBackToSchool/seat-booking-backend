package org.itss.backtoschool.deskops.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.itss.backtoschool.deskops.config.WeatherApiConfig;
import org.itss.backtoschool.deskops.dto.external.weather.OpenWeatherMapResponse;
import org.itss.backtoschool.deskops.exception.weather.WeatherApiException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class OpenWeatherMapClient implements WeatherClient {

    private final RestTemplate restTemplate;
    private final WeatherApiConfig weatherApiConfig;

    @Override
    public OpenWeatherMapResponse fetchForecast(Double latitude, Double longitude, Integer days) {
        try {
            String url = buildForecastUrl(latitude, longitude, days);
            log.info("Fetching weather forecast from: {}", url);

            OpenWeatherMapResponse response = restTemplate.getForObject(url, OpenWeatherMapResponse.class);

            if (response == null || response.getForecastList() == null) {
                throw new WeatherApiException("Empty response from weather API");
            }

            log.info("Successfully fetched weather data for coordinates: {}, {}", latitude, longitude);
            return response;

        } catch (Exception e) {
            log.error("Failed to fetch weather data: {}", e.getMessage());
            throw new WeatherApiException("Failed to fetch weather data from OpenWeatherMap", e);
        }
    }

    private String buildForecastUrl(Double latitude, Double longitude, Integer days) {
        int count = (days != null && days > 0) ? days * 8 : 40;

        return UriComponentsBuilder.fromUriString(weatherApiConfig.getBaseUrl() + "/forecast")
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)
                .queryParam("cnt", count)
                .queryParam("appid", weatherApiConfig.getApiKey())
                .queryParam("units", "metric")
                .build()
                .toUriString();
    }
}