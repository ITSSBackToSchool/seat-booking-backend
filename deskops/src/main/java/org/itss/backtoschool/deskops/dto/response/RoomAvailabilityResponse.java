package org.itss.backtoschool.deskops.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.itss.backtoschool.deskops.dto.TimeSlotDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Response containing the full availability timeline for a room on a specific date.
 * Shows all booked and available time slots from business hours start to end.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomAvailabilityResponse {
    private Long roomId;
    private String roomName;
    private String roomType;
    private LocalDate date;

    // Business hours configuration
    private BusinessHours businessHours;

    // Complete timeline of slots (both BOOKED and AVAILABLE)
    private List<TimeSlotDTO> timeSlots;

    // Summary information
    private Boolean isEntirelyAvailable;  // True if no bookings at all
    private Boolean isEntirelyBooked;     // True if completely booked
    private TimeSlotDTO nextAvailableSlot;  // Next free slot (null if none)
    private Integer totalAvailableMinutes;  // Total free time in minutes

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BusinessHours {
        private LocalTime start;  // e.g., 09:00
        private LocalTime end;    // e.g., 17:00
    }
}
