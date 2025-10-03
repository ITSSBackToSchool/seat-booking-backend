package org.itss.backtoschool.deskops.dto.response;

import lombok.Data;

@Data
public class Geometry {
    private String type;
    private Object coordinates; // Can be List<Double> (Point) or List<List<Double>> (LineString/Polygon)
}
