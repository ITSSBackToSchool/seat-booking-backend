package org.itss.backtoschool.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrafficIncident {
    private String type;
    private String description;
    private TrafficCoordinate location;
    private Integer delayInSeconds;
    private String severity;
}
