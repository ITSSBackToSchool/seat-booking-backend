package org.itss.backtoschool.deskops.exception.weather;

public class WeatherDataNotFoundException extends RuntimeException {
    public WeatherDataNotFoundException() {
        super("Weather data not found");
    }

    public WeatherDataNotFoundException(String message) {
        super(message);
    }
}