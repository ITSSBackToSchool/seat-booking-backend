package org.itss.backtoschool.deskops.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "weather_data")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class WeatherData extends CommonEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    @ToString.Exclude
    private Location location;

    @Column(name = "weather_date", nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Double temperature;

    private Double feelsLike;

    @Column(name = "weather_condition", nullable = false)
    private String condition;

    private String description;

    private Integer humidity;

    private Double windSpeed;

    private String iconCode;

    @Column(nullable = false)
    private LocalDateTime fetchedAt;
}
