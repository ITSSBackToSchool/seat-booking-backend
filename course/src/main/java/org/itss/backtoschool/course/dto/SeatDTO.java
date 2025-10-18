package org.itss.backtoschool.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatDTO {
    private Long id;
    private String seatNumber;
    private String roomName;
    private String floorName;
    private String buildingName;
}
