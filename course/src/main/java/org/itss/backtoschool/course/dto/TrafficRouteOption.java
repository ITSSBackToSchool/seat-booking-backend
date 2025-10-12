package org.itss.backtoschool.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrafficRouteOption {
    private String routeName;
    private Integer durationInSeconds;
    private Integer distanceInMeters;
    private Integer delayInSeconds;
    private String durationDisplay;
    private String distanceDisplay;
    private List<TrafficCoordinate> geometry;
    private String summary;
    private Boolean isBestRoute;
}
