package org.itss.backtoschool.deskops.dto.external.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherMapResponse {

    @JsonProperty("list")
    private List<ForecastItem> forecastList;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ForecastItem {
        @JsonProperty("dt")
        private Long timestamp;

        @JsonProperty("main")
        private MainData main;

        @JsonProperty("weather")
        private List<WeatherInfo> weather;

        @JsonProperty("wind")
        private WindData wind;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MainData {
        @JsonProperty("temp")
        private Double temperature;

        @JsonProperty("feels_like")
        private Double feelsLike;

        @JsonProperty("humidity")
        private Integer humidity;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WeatherInfo {
        @JsonProperty("main")
        private String condition;

        @JsonProperty("description")
        private String description;

        @JsonProperty("icon")
        private String icon;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WindData {
        @JsonProperty("speed")
        private Double speed;
    }
}
