package org.itss.backtoschool.deskops.service;

import org.itss.backtoschool.deskops.dto.CreateRoomRequest;
import org.itss.backtoschool.deskops.dto.RoomDTO;
import org.itss.backtoschool.deskops.dto.response.RoomAvailabilityResponse;

import java.time.LocalDate;

public interface RoomService {
    RoomDTO createRoom(CreateRoomRequest request);

    /**
     * Get the complete availability timeline for a room on a specific date.
     * Shows all booked and available time slots.
     *
     * @param roomId the room ID
     * @param date the date to check availability for
     * @return complete timeline with booked and available slots
     */
    RoomAvailabilityResponse getRoomAvailability(Long roomId, LocalDate date);
}
