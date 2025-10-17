package org.itss.backtoschool.course.dto.response;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class WeatherResponse {
    private double latitude;
    private double longitude;
    private double generationtime_ms;
    private int utc_offset_seconds;
    private String timezone;
    private String timezone_abbreviation;
    private double elevation;
    private Map<String, String> current_units;
    private CurrentWeather current;
    private Map<String, String> daily_units;
    private DailyWeather daily;

    @Getter
    @Setter
    public static class CurrentWeather {
        private String time;
        private int interval;
        private double temperature_2m;
        private int relative_humidity_2m;
        private double precipitation;
        private double surface_pressure;
        private double wind_speed_10m;
        private double wind_direction_10m;
    }

    @Getter
    @Setter
    public static class DailyWeather {
        private List<String> time;
        private List<Double> temperature_2m_max;
        private List<Double> temperature_2m_min;
        private List<Double> precipitation_sum;
        private List<Double> wind_speed_10m_max;
        private List<Double> wind_direction_10m_dominant;
    }
}