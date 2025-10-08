package org.itss.backtoschool.deskops.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

/**
 * Represents a time slot in a room's availability timeline.
 * Can be either AVAILABLE (free to book) or BOOKED (already reserved).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlotDTO {
    private LocalTime startTime;
    private LocalTime endTime;
    private String status;  // "AVAILABLE" or "BOOKED"

    // Only populated if status = "BOOKED"
    private String bookedBy;  // User name
    private String bookedByEmail;  // User email
    private Long bookingId;  // Reservation ID
    private Integer durationMinutes;  // Duration of this slot
}
