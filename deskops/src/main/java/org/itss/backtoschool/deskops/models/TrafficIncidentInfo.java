package org.itss.backtoschool.deskops.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrafficIncidentInfo {
    private int totalIncidents;
    private int severeIncidents;
}
