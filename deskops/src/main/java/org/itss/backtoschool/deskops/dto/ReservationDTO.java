package org.itss.backtoschool.deskops.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private Long id;
    private LocalDate reservationDate;

    // Time slot information
    private LocalTime startTime;
    private LocalTime endTime;
    private Boolean isAllDayBooking;  // Computed: true if startTime/endTime are null

    // Entire room booking info
    private Boolean bookEntireRoom;
    private String roomType;  // DESK_ROOM, CONFERENCE_ROOM, etc.

    private String status;
    private Long seatId;
    private String seatNumber;
    private String roomName;
    private Long roomId;  // Added for frontend convenience
    private String floorName;
    private String buildingName;
    private Long userId;
    private String userName;
    private String userEmail;
    private WeatherDTO weather;
    private TrafficDTO traffic;
}
