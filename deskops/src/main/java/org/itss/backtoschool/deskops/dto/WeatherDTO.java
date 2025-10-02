package org.itss.backtoschool.deskops.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeatherDTO {
    private Long id;
    private LocalDate date;
    private Double temperature;
    private Double feelsLike;
    private String condition;
    private String description;
    private Integer humidity;
    private Double windSpeed;
    private String iconCode;
    private String buildingName;
    private String city;
}
