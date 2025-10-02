package org.itss.backtoschool.deskops.dto.response;

import lombok.Data;

@Data
public class FlowSegmentData {
    private double currentSpeed;
    private double freeFlowSpeed;
    private int currentTravelTime;
    private int freeFlowTravelTime;
}
