package org.itss.backtoschool.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatAvailabilityDTO {
    private Long id;
    private String seatNumber;
    private String roomName;
    private String floorName;
    private String buildingName;
    private Boolean isAvailable;
    private String reservedBy;
    private String reservedTime;
}

