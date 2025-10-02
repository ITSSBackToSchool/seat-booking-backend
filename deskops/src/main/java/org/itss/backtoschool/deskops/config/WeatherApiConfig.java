package org.itss.backtoschool.deskops.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "weather")
public class WeatherApiConfig {
    private String provider;
    private String apiKey;
    private String baseUrl;
    private Integer timeoutSeconds;
    private CacheConfig cache;
    private DefaultLocationConfig defaultLocation;

    @Data
    public static class CacheConfig {
        private Integer currentTtlMinutes;
        private Integer forecastTtlMinutes;
    }

    @Data
    public static class DefaultLocationConfig {
        private String city;
        private Double latitude;
        private Double longitude;
    }
}