package org.itss.backtoschool.deskops.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RouteInfo {
    private int travelTimeSeconds;
    private int trafficDelaySeconds;
    private int lengthMeters;
}
