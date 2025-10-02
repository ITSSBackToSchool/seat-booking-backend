package org.itss.backtoschool.course.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrafficResponse {
    private String start;
    private String end;
    private double distanceKm;
    private double normalDurationMin;
    private double trafficDurationMin;
    private double trafficDelayMin;
    private boolean isTraffic;
    private String trafficLevel;
}
