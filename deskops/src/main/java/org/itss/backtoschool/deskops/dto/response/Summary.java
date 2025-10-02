package org.itss.backtoschool.deskops.dto.response;

import lombok.Data;

@Data
public class Summary {
    private int lengthInMeters;
    private int travelTimeInSeconds;
    private int trafficDelayInSeconds;
}
