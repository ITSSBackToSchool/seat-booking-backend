package org.itss.backtoschool.deskops.dto.response;

import lombok.Data;

@Data
public class Incident {
    private String type;
    private Geometry geometry;
    private Properties properties;
}
