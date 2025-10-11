package org.itss.backtoschool.course.dto;

public class WeatherDTO {
    private String description;
    private double temperature;

    public WeatherDTO(String description, double temperature) {
        this.description = description;
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public double getTemperature() {
        return temperature;
    }
}
