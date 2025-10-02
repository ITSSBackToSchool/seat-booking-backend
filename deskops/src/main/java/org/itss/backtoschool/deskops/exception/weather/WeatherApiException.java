package org.itss.backtoschool.deskops.exception.weather;

public class WeatherApiException extends RuntimeException {
    public WeatherApiException() {
        super("Failed to fetch weather data from external API");
    }

    public WeatherApiException(String message) {
        super(message);
    }

    public WeatherApiException(String message, Throwable cause) {
        super(message, cause);
    }
}