package org.itss.backtoschool.deskops.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.itss.backtoschool.deskops.dto.CreateRoomRequest;
import org.itss.backtoschool.deskops.dto.RoomDTO;
import org.itss.backtoschool.deskops.dto.TimeSlotDTO;
import org.itss.backtoschool.deskops.dto.response.RoomAvailabilityResponse;
import org.itss.backtoschool.deskops.entities.*;
import org.itss.backtoschool.deskops.repository.FloorRepository;
import org.itss.backtoschool.deskops.repository.ReservationRepository;
import org.itss.backtoschool.deskops.repository.RoomRepository;
import org.itss.backtoschool.deskops.service.RoomService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private static final LocalTime BUSINESS_HOURS_START = LocalTime.of(9, 0);
    private static final LocalTime BUSINESS_HOURS_END = LocalTime.of(17, 0);

    private final RoomRepository roomRepository;
    private final FloorRepository floorRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public RoomDTO createRoom(CreateRoomRequest request) {
        Floor floor = floorRepository.findById(request.getFloorId())
                .orElseThrow(() -> new IllegalArgumentException("Floor not found: " + request.getFloorId()));

        Room room = new Room();
        room.setName(request.getName());
        room.setSeatCount(request.getSeatCount());
        room.setRoomType(RoomType.valueOf(request.getRoomType().name()));
        room.setFloor(floor);

        Room saved = roomRepository.save(room);

        RoomDTO dto = new RoomDTO();
        dto.setId(saved.getId());
        dto.setName(saved.getName());
        dto.setSeatCount(saved.getSeatCount());
        dto.setRoomType(saved.getRoomType().name());
        dto.setFloorId(floor.getId());

        return dto;
    }

    @Override
    public RoomAvailabilityResponse getRoomAvailability(Long roomId, LocalDate date) {
        log.debug("Getting availability for room {} on date {}", roomId, date);

        // Get the room
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Room not found: " + roomId));

        // For DESK_ROOM, availability timeline doesn't apply (all-day bookings only)
        if (room.getRoomType() == RoomType.DESK_ROOM) {
            throw new IllegalArgumentException("Availability timeline is only for conference, collaborative, and recreational rooms");
        }

        // Get all ACTIVE reservations for this room on the specified date
        List<Reservation> reservations = reservationRepository.findByRoomAndDateAndStatus(
                roomId, date, ReservationStatus.ACTIVE
        );

        // Sort reservations by start time
        reservations.sort(Comparator.comparing(Reservation::getStartTime));

        // Build the timeline
        List<TimeSlotDTO> timeline = buildTimeline(reservations);

        // Calculate summary information
        boolean isEntirelyAvailable = reservations.isEmpty();
        boolean isEntirelyBooked = calculateIfEntirelyBooked(timeline);
        TimeSlotDTO nextAvailableSlot = findNextAvailableSlot(timeline);
        int totalAvailableMinutes = calculateTotalAvailableMinutes(timeline);

        return RoomAvailabilityResponse.builder()
                .roomId(room.getId())
                .roomName(room.getName())
                .roomType(room.getRoomType().name())
                .date(date)
                .businessHours(RoomAvailabilityResponse.BusinessHours.builder()
                        .start(BUSINESS_HOURS_START)
                        .end(BUSINESS_HOURS_END)
                        .build())
                .timeSlots(timeline)
                .isEntirelyAvailable(isEntirelyAvailable)
                .isEntirelyBooked(isEntirelyBooked)
                .nextAvailableSlot(nextAvailableSlot)
                .totalAvailableMinutes(totalAvailableMinutes)
                .build();
    }

    /**
     * Build a complete timeline showing all BOOKED and AVAILABLE slots.
     */
    private List<TimeSlotDTO> buildTimeline(List<Reservation> reservations) {
        List<TimeSlotDTO> timeline = new ArrayList<>();
        LocalTime currentTime = BUSINESS_HOURS_START;

        for (Reservation reservation : reservations) {
            // If there's a gap before this reservation, add an AVAILABLE slot
            if (currentTime.isBefore(reservation.getStartTime())) {
                timeline.add(TimeSlotDTO.builder()
                        .startTime(currentTime)
                        .endTime(reservation.getStartTime())
                        .status("AVAILABLE")
                        .durationMinutes((int) Duration.between(currentTime, reservation.getStartTime()).toMinutes())
                        .build());
            }

            // Add the BOOKED slot
            timeline.add(TimeSlotDTO.builder()
                    .startTime(reservation.getStartTime())
                    .endTime(reservation.getEndTime())
                    .status("BOOKED")
                    .bookedBy(reservation.getUser().getName())
                    .bookedByEmail(reservation.getUser().getEmail())
                    .bookingId(reservation.getId())
                    .durationMinutes((int) Duration.between(reservation.getStartTime(), reservation.getEndTime()).toMinutes())
                    .build());

            currentTime = reservation.getEndTime();
        }

        // If there's time left after the last reservation, add an AVAILABLE slot
        if (currentTime.isBefore(BUSINESS_HOURS_END)) {
            timeline.add(TimeSlotDTO.builder()
                    .startTime(currentTime)
                    .endTime(BUSINESS_HOURS_END)
                    .status("AVAILABLE")
                    .durationMinutes((int) Duration.between(currentTime, BUSINESS_HOURS_END).toMinutes())
                    .build());
        }

        return timeline;
    }

    /**
     * Check if the room is entirely booked (no available slots).
     */
    private boolean calculateIfEntirelyBooked(List<TimeSlotDTO> timeline) {
        return timeline.stream().noneMatch(slot -> "AVAILABLE".equals(slot.getStatus()));
    }

    /**
     * Find the next available slot (or null if none).
     */
    private TimeSlotDTO findNextAvailableSlot(List<TimeSlotDTO> timeline) {
        return timeline.stream()
                .filter(slot -> "AVAILABLE".equals(slot.getStatus()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Calculate total available time in minutes.
     */
    private int calculateTotalAvailableMinutes(List<TimeSlotDTO> timeline) {
        return timeline.stream()
                .filter(slot -> "AVAILABLE".equals(slot.getStatus()))
                .mapToInt(TimeSlotDTO::getDurationMinutes)
                .sum();
    }
}
