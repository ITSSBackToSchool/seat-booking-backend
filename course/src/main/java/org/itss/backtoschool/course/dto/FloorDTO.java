package org.itss.backtoschool.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FloorDTO {
    private Long id;
    private String name;
    private Long buildingId;
    private String buildingName;
}

