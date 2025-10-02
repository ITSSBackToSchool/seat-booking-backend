package org.itss.backtoschool.deskops.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrafficFlowInfo {
    private double currentSpeed;
    private double freeFlowSpeed;
    private int currentTravelTime;
    private int freeFlowTravelTime;
}
