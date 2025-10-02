package org.itss.backtoschool.course.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrafficRouteRequest {
    private String originAddress;
    private Double originLatitude;
    private Double originLongitude;
    private Integer maxAlternatives; // Number of alternative routes (default: 3)
    private Boolean includeTraffic;  // Include traffic data (default: true)
}