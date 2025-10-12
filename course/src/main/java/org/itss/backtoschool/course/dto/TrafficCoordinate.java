package org.itss.backtoschool.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrafficCoordinate {
    private Double latitude;
    private Double longitude;
}