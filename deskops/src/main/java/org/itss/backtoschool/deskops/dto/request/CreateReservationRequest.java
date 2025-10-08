package org.itss.backtoschool.deskops.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservationRequest {

    @NotNull(message = "Seat IDs are required")
    private List<Long> seatIds;

    @NotNull(message = "Reservation date is required")
    private LocalDate reservationDate;

    // Time slot fields - required for CONFERENCE_ROOM/COLLABORATIVE/RECREATIONAL, null for DESK_ROOM
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;  // e.g., "09:00", "14:30"

    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;    // e.g., "11:00", "15:30"

    // Flag to book entire conference room (all seats) - default false
    private Boolean bookEntireRoom = false;
}
