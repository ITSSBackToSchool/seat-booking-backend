package org.itss.backtoschool.deskops.validator;

import lombok.extern.slf4j.Slf4j;
import org.itss.backtoschool.deskops.dto.request.CreateReservationRequest;
import org.itss.backtoschool.deskops.entities.Room;
import org.itss.backtoschool.deskops.entities.RoomType;
import org.itss.backtoschool.deskops.exception.reservation.InvalidReservationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalTime;

/**
 * Validator for reservation requests with room-type-specific rules.
 * Rules:
 * - DESK_ROOM: All-day bookings (no time slots), no entire room booking
 * - CONFERENCE_ROOM: Time slots required (09:00-17:00), max 8 hours, optional entire room booking
 * - COLLABORATIVE: Time slots required (09:00-17:00), max 1 hour
 * - RECREATIONAL: Time slots required (09:00-17:00), max 1 hour
 * All time slots must be in 30-minute increments (09:00, 09:30, 10:00, etc.)
 */
@Slf4j
@Component
public class ReservationValidator {

    private static final LocalTime BUSINESS_HOURS_START = LocalTime.of(9, 0);  // 09:00
    private static final LocalTime BUSINESS_HOURS_END = LocalTime.of(17, 0);   // 17:00
    private static final int TIME_SLOT_INCREMENT_MINUTES = 30;
    private static final long MIN_DURATION_MINUTES = 30;
    private static final long MAX_CONFERENCE_DURATION_MINUTES = 480;  // 8 hours
    private static final long MAX_COLLABORATIVE_RECREATIONAL_DURATION_MINUTES = 60;  // 1 hour

    /**
     * Validates a reservation request based on the room type.
     *
     * @param request the reservation request to validate
     * @param room the room being booked
     * @throws InvalidReservationException if validation fails
     */
    public void validateReservationRequest(CreateReservationRequest request, Room room) {
        RoomType roomType = room.getRoomType();
        log.debug("Validating reservation request for room type: {}", roomType);

        if (roomType == RoomType.DESK_ROOM) {
            validateDeskRoomBooking(request);
        } else {
            // CONFERENCE_ROOM, COLLABORATIVE, RECREATIONAL require time slots
            validateTimeSlotBooking(request, roomType);
        }
    }

    /**
     * Validates desk room bookings (all-day, no time slots).
     */
    private void validateDeskRoomBooking(CreateReservationRequest request) {
        if (request.getStartTime() != null || request.getEndTime() != null) {
            throw new InvalidReservationException(
                "Desk rooms are booked all-day and do not support time slots"
            );
        }

        if (request.getBookEntireRoom() != null && request.getBookEntireRoom()) {
            throw new InvalidReservationException(
                "Cannot book entire desk room"
            );
        }
    }

    /**
     * Validates time slot bookings for conference/collaborative/recreational rooms.
     */
    private void validateTimeSlotBooking(CreateReservationRequest request, RoomType roomType) {
        validateTimeSlotRequired(request);
        validateTimeSlotOrder(request);
        validateBusinessHours(request);
        validateTimeSlotIncrements(request);
        validateDurationLimits(request, roomType);
    }

    /**
     * Ensures time slots are provided.
     */
    private void validateTimeSlotRequired(CreateReservationRequest request) {
        if (request.getStartTime() == null || request.getEndTime() == null) {
            throw new InvalidReservationException(
                "Start time and end time are required for conference rooms, collaborative spaces, and recreational rooms"
            );
        }
    }

    /**
     * Ensures end time is after start time.
     */
    private void validateTimeSlotOrder(CreateReservationRequest request) {
        if (!request.getEndTime().isAfter(request.getStartTime())) {
            throw new InvalidReservationException(
                "End time must be after start time"
            );
        }
    }

    /**
     * Ensures booking is within business hours (09:00 - 17:00).
     */
    private void validateBusinessHours(CreateReservationRequest request) {
        if (request.getStartTime().isBefore(BUSINESS_HOURS_START)) {
            throw new InvalidReservationException(
                String.format("Start time cannot be before business hours start (%s)",
                    BUSINESS_HOURS_START)
            );
        }

        if (request.getEndTime().isAfter(BUSINESS_HOURS_END)) {
            throw new InvalidReservationException(
                String.format("End time cannot be after business hours end (%s)",
                    BUSINESS_HOURS_END)
            );
        }
    }

    /**
     * Ensures time slots are in 30-minute increments.
     */
    private void validateTimeSlotIncrements(CreateReservationRequest request) {
        if (request.getStartTime().getMinute() % TIME_SLOT_INCREMENT_MINUTES != 0) {
            throw new InvalidReservationException(
                String.format("Start time must be in %d-minute increments (e.g., 09:00, 09:30, 10:00)",
                    TIME_SLOT_INCREMENT_MINUTES)
            );
        }

        if (request.getEndTime().getMinute() % TIME_SLOT_INCREMENT_MINUTES != 0) {
            throw new InvalidReservationException(
                String.format("End time must be in %d-minute increments (e.g., 09:00, 09:30, 10:00)",
                    TIME_SLOT_INCREMENT_MINUTES)
            );
        }
    }

    /**
     * Validates duration limits based on room type.
     */
    private void validateDurationLimits(CreateReservationRequest request, RoomType roomType) {
        long durationMinutes = Duration.between(
            request.getStartTime(),
            request.getEndTime()
        ).toMinutes();

        // Minimum 30 minutes for all time-slot bookings
        if (durationMinutes < MIN_DURATION_MINUTES) {
            throw new InvalidReservationException(
                String.format("Minimum booking duration is %d minutes", MIN_DURATION_MINUTES)
            );
        }

        // Maximum limits based on room type
        switch (roomType) {
            case COLLABORATIVE:
            case RECREATIONAL:
                if (durationMinutes > MAX_COLLABORATIVE_RECREATIONAL_DURATION_MINUTES) {
                    throw new InvalidReservationException(
                        String.format("%s rooms can be booked for maximum %d minutes (1 hour)",
                            roomType, MAX_COLLABORATIVE_RECREATIONAL_DURATION_MINUTES)
                    );
                }
                break;

            case CONFERENCE_ROOM:
                if (durationMinutes > MAX_CONFERENCE_DURATION_MINUTES) {
                    throw new InvalidReservationException(
                        String.format("Conference rooms can be booked for maximum %d minutes (8 hours)",
                            MAX_CONFERENCE_DURATION_MINUTES)
                    );
                }
                break;

            default:
                // Should not reach here due to earlier validation
                break;
        }
    }
}
