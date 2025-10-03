package org.itss.backtoschool.deskops.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.itss.backtoschool.deskops.client.WeatherClient;
import org.itss.backtoschool.deskops.config.WeatherApiConfig;
import org.itss.backtoschool.deskops.dto.WeatherDTO;
import org.itss.backtoschool.deskops.dto.external.weather.OpenWeatherMapResponse;
import org.itss.backtoschool.deskops.entities.Location;
import org.itss.backtoschool.deskops.entities.WeatherData;
import org.itss.backtoschool.deskops.exception.weather.LocationNotConfiguredException;
import org.itss.backtoschool.deskops.exception.weather.WeatherDataNotFoundException;
import org.itss.backtoschool.deskops.mapper.WeatherMapper;
import org.itss.backtoschool.deskops.repository.LocationRepository;
import org.itss.backtoschool.deskops.repository.WeatherDataRepository;
import org.itss.backtoschool.deskops.service.WeatherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final LocationRepository locationRepository;
    private final WeatherDataRepository weatherDataRepository;
    private final WeatherClient weatherClient;
    private final WeatherMapper weatherMapper;
    private final WeatherApiConfig weatherApiConfig;
    private final org.springframework.context.ApplicationContext applicationContext;

    @Override
    @Transactional(readOnly = true)
    public WeatherDTO getWeatherForDate(Long buildingId, LocalDate date) {
        Location location = getLocationByBuildingId(buildingId);

        var cachedWeather = weatherDataRepository.findByLocationIdAndDate(location.getId(), date);

        if (cachedWeather.isPresent() && isCacheValid(cachedWeather.get())) {
            log.info("Returning cached weather for building {} on date {}", buildingId, date);
            return weatherMapper.toDTO(cachedWeather.get());
        }

        WeatherService proxy = applicationContext.getBean(WeatherService.class);
        return ((WeatherServiceImpl) proxy).fetchAndCacheWeatherForDate(location, date);
    }

    @Override
    @Transactional
    public List<WeatherDTO> getWeatherForecast(Long buildingId, Integer days) {
        Location location = getLocationByBuildingId(buildingId);
        int forecastDays = (days != null && days > 0) ? days : 5;

        OpenWeatherMapResponse response = weatherClient.fetchForecast(
                location.getLatitude(),
                location.getLongitude(),
                forecastDays
        );

        List<WeatherDTO> forecast = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (int i = 0; i < forecastDays; i++) {
            LocalDate targetDate = today.plusDays(i);
            WeatherData weatherData = findOrCreateWeatherDataForDate(response, location, targetDate);

            if (weatherData != null) {
                forecast.add(weatherMapper.toDTO(weatherData));
            }
        }

        return forecast;
    }

    private Location getLocationByBuildingId(Long buildingId) {
        return locationRepository.findByBuildingId(buildingId)
                .orElseThrow(() -> new LocationNotConfiguredException(
                        "Location not configured for building ID: " + buildingId));
    }

    @Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRES_NEW)
    protected WeatherDTO fetchAndCacheWeatherForDate(Location location, LocalDate date) {
        log.info("Fetching weather from external API for location {} on date {}", location.getCity(), date);

        LocalDate today = LocalDate.now();
        LocalDate maxForecastDate = today.plusDays(5);

        if (date.isAfter(maxForecastDate)) {
            log.warn("Weather forecast not available for date {} (beyond 5-day forecast range)", date);
            throw new WeatherDataNotFoundException("Weather forecast only available for the next 5 days");
        }

        OpenWeatherMapResponse response = weatherClient.fetchForecast(
                location.getLatitude(),
                location.getLongitude(),
                5
        );

        WeatherData weatherData = findOrCreateWeatherDataForDate(response, location, date);

        if (weatherData == null) {
            throw new WeatherDataNotFoundException("No weather data available for date: " + date);
        }

        return weatherMapper.toDTO(weatherData);
    }

    @Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRES_NEW)
    protected WeatherData findOrCreateWeatherDataForDate(OpenWeatherMapResponse response, Location location, LocalDate targetDate) {
        var existing = weatherDataRepository.findByLocationIdAndDate(location.getId(), targetDate);
        if (existing.isPresent() && isCacheValid(existing.get())) {
            return existing.get();
        }

        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime targetDateTime = targetDate.atTime(12, 0);

        OpenWeatherMapResponse.ForecastItem closestItem = response.getForecastList().stream()
                .filter(item -> {
                    LocalDate itemDate = Instant.ofEpochSecond(item.getTimestamp())
                            .atZone(zoneId)
                            .toLocalDate();
                    return itemDate.equals(targetDate);
                })
                .findFirst()
                .orElse(null);

        if (closestItem == null || closestItem.getWeather().isEmpty()) {
            log.warn("No weather data found for date: {}", targetDate);
            return null;
        }

        WeatherData weatherData = WeatherData.builder()
                .location(location)
                .date(targetDate)
                .temperature(closestItem.getMain().getTemperature())
                .feelsLike(closestItem.getMain().getFeelsLike())
                .condition(closestItem.getWeather().getFirst().getCondition())
                .description(closestItem.getWeather().getFirst().getDescription())
                .humidity(closestItem.getMain().getHumidity())
                .windSpeed(closestItem.getWind().getSpeed())
                .iconCode(closestItem.getWeather().getFirst().getIcon())
                .fetchedAt(LocalDateTime.now())
                .build();

        existing.ifPresent(weatherDataRepository::delete);

        return weatherDataRepository.save(weatherData);
    }

    private boolean isCacheValid(WeatherData weatherData) {
        LocalDateTime now = LocalDateTime.now();
        LocalDate today = LocalDate.now();

        if (weatherData.getDate().isBefore(today)) {
            return true;
        }

        int ttlMinutes = weatherData.getDate().equals(today)
                ? weatherApiConfig.getCache().getCurrentTtlMinutes()
                : weatherApiConfig.getCache().getForecastTtlMinutes();

        return weatherData.getFetchedAt().plusMinutes(ttlMinutes).isAfter(now);
    }
}