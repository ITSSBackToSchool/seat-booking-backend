package org.itss.backtoschool.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationSeatDTO {
    private Long id;
    private LocalDate reservationDate;
    private String status;
    private Long seatId;
    private String seatNumber;
    private String roomName;
    private String floorName;
    private String buildingName;
    private Long userId;
    private String userName;
    private String userEmail;
    private LocalTime startTime;
    private LocalTime endTime;
}
