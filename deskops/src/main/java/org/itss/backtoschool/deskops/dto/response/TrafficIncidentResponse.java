package org.itss.backtoschool.deskops.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class TrafficIncidentResponse {
    private List<Incident> incidents;
}

@Data
class Incident {
    private String type;
    private Geometry geometry;
    private Properties properties;
}

@Data
class Geometry {
    private String type;
    private List<List<Double>> coordinates;
}

@Data
class Properties {
    private int iconCategory;
    private String id;
    private int magnitudeOfDelay;
}
