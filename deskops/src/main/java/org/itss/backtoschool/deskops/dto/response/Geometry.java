package org.itss.backtoschool.deskops.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class Geometry {
    private String type;
    private List<List<Double>> coordinates;
}
