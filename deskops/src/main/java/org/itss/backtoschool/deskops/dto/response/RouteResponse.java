package org.itss.backtoschool.deskops.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class RouteResponse {
    private List<Route> routes;
}

@Data
class Route {
    private Summary summary;
}

@Data
class Summary {
    private int lengthInMeters;
    private int travelTimeInSeconds;
    private int trafficDelayInSeconds;
}
