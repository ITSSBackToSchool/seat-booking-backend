package org.itss.backtoschool.deskops.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class GeocodeAPIResponse {
    private List<GeocodeResult> results;
}

@Data
class GeocodeResult {
    private Position position;
}

@Data
class Position {
    private double lat;
    private double lon;
}
