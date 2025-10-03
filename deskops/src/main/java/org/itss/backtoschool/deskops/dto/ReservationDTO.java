package org.itss.backtoschool.deskops.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
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
    private WeatherDTO weather;
    private TrafficDTO traffic;
}
