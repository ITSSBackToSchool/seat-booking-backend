package org.itss.backtoschool.deskops.dto.response;

import lombok.Data;

@Data
public class TrafficFlowResponse {
    private FlowSegmentData flowSegmentData;
}

@Data
class FlowSegmentData {
    private double currentSpeed;
    private double freeFlowSpeed;
    private int currentTravelTime;
    private int freeFlowTravelTime;
}
