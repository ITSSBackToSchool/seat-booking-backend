package org.itss.backtoschool.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private Long id;
    private LocalDateTime reservationDateStart;
    private LocalDateTime reservationDateEnd;
    private String status;
    private Long seatId;
    private String seatNumber;
    private String roomName;
    private String floorName;
    private String buildingName;
    private Long userId;
    private String userName;
    private String userEmail;
}
