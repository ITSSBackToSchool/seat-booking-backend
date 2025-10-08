package org.itss.backtoschool.deskops.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.itss.backtoschool.deskops.dto.TrafficDTO;
import org.itss.backtoschool.deskops.dto.WeatherDTO;
import org.itss.backtoschool.deskops.dto.request.CreateReservationRequest;
import org.itss.backtoschool.deskops.dto.response.CreateReservationResponse;
import org.itss.backtoschool.deskops.entities.*;
import org.itss.backtoschool.deskops.exception.auth.UnauthorizedException;
import org.itss.backtoschool.deskops.exception.reservation.InvalidReservationDateException;
import org.itss.backtoschool.deskops.exception.reservation.ReservationNotFoundException;
import org.itss.backtoschool.deskops.exception.seat.SeatAlreadyReservedException;
import org.itss.backtoschool.deskops.exception.seat.SeatNotFoundException;
import org.itss.backtoschool.deskops.mapper.ReservationMapper;
import org.itss.backtoschool.deskops.repository.ReservationRepository;
import org.itss.backtoschool.deskops.repository.RoomRepository;
import org.itss.backtoschool.deskops.repository.SeatRepository;
import org.itss.backtoschool.deskops.service.ReservationService;
import org.itss.backtoschool.deskops.service.TrafficService;
import org.itss.backtoschool.deskops.service.UserService;
import org.itss.backtoschool.deskops.service.WeatherService;
import org.itss.backtoschool.deskops.validator.ReservationValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final SeatRepository seatRepository;
    private final RoomRepository roomRepository;
    private final ReservationMapper reservationMapper;
    private final WeatherService weatherService;
    private final TrafficService trafficService;
    private final UserService userService;
    private final ReservationValidator reservationValidator;


    @Override
    @Transactional
    public CreateReservationResponse createReservations(CreateReservationRequest request, Jwt jwt) {
        var user = userService.getOrCreateUser(jwt);
        var createdReservations = new ArrayList<Reservation>();

        // Get the first seat to determine the room and validate request
        Long firstSeatId = request.getSeatIds().getFirst();
        Seat firstSeat = seatRepository.findById(firstSeatId)
                .orElseThrow(SeatNotFoundException::new);
        Room room = firstSeat.getRoom();

        // Validate request based on room type
        reservationValidator.validateReservationRequest(request, room);

        // Determine which seats to book
        List<Long> seatsToBook;
        if (Boolean.TRUE.equals(request.getBookEntireRoom())) {
            // Book ALL seats in the room
            seatsToBook = getAllSeatIdsInRoom(room.getId());
            log.info("Booking entire room '{}' with {} seats for user {}",
                room.getName(), seatsToBook.size(), user.getId());
        } else {
            // Book only requested seats
            seatsToBook = request.getSeatIds();
            log.debug("Booking {} specific seats for user {}", seatsToBook.size(), user.getId());
        }

        // Create reservations for each seat
        seatsToBook.forEach(seatId ->
            processReservation(
                seatId,
                user,
                request.getReservationDate(),
                request.getStartTime(),
                request.getEndTime(),
                Boolean.TRUE.equals(request.getBookEntireRoom()),
                createdReservations
            )
        );

        // Eagerly fetch nested entities to avoid lazy loading issues
        for (Reservation createdReservation : createdReservations) {
            eagerlyFetchNestedEntities(createdReservation);
        }

        // Map to DTOs and enrich with weather/traffic
        var reservationDTOs = createdReservations.stream()
                .map(reservation -> {
                    var dto = reservationMapper.toDTO(reservation);
                    enrichWithWeather(dto, reservation);
                    enrichWithTraffic(dto, reservation);
                    return dto;
                })
                .toList();

        return CreateReservationResponse.builder()
                .reservations(reservationDTOs)
                .build();
    }

    /**
     * Get all seat IDs in a room (used for booking entire conference room).
     */
    private List<Long> getAllSeatIdsInRoom(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + roomId));

        return room.getSeats().stream()
                .map(Seat::getId)
                .toList();
    }

    private void eagerlyFetchNestedEntities(Reservation reservation) {
        @SuppressWarnings("unused")
        var building = reservation.getSeat().getRoom().getFloor().getBuilding();
    }

    private void enrichWithWeather(org.itss.backtoschool.deskops.dto.ReservationDTO dto, Reservation reservation) {
        try {
            Long buildingId = reservation.getSeat().getRoom().getFloor().getBuilding().getId();
            WeatherDTO weather = weatherService.getWeatherForDate(buildingId, reservation.getReservationDate());
            dto.setWeather(weather);
        } catch (Exception e) {
            log.error("Failed to fetch weather for reservation {}: {}", reservation.getId(), e.getMessage(), e);
        }
    }

    private void enrichWithTraffic(org.itss.backtoschool.deskops.dto.ReservationDTO dto, Reservation reservation) {
        try {
            LocalDate today = LocalDate.now();
            LocalDate reservationDate = reservation.getReservationDate();

            if (reservationDate.isBefore(today)) {
                log.debug("Skipping traffic for past reservation {}", reservation.getId());
                dto.setTraffic(TrafficDTO.builder()
                        .available(false)
                        .unavailableReason("Traffic data not available for past reservations")
                        .build());
                return;
            }

            if (reservationDate.isAfter(today.plusDays(5))) {
                log.debug("Skipping traffic for distant reservation {} (more than 5 days away)", reservation.getId());
                dto.setTraffic(TrafficDTO.builder()
                        .available(false)
                        .unavailableReason("Traffic data not available for reservations more than 5 days in advance")
                        .build());
                return;
            }

            Long buildingId = reservation.getSeat().getRoom().getFloor().getBuilding().getId();
            TrafficDTO traffic = trafficService.getTrafficForUser(reservation.getUser(), buildingId);
            dto.setTraffic(traffic);
        } catch (Exception e) {
            log.error("Failed to fetch traffic for reservation {}: {}", reservation.getId(), e.getMessage(), e);
            dto.setTraffic(TrafficDTO.builder()
                    .available(false)
                    .unavailableReason(e.getMessage())
                    .build());
        }
    }

    private void verifyOwnership(Reservation reservation, User currentUser) {
        if (!reservation.getUser().getId().equals(currentUser.getId())) {
            throw new UnauthorizedException("You can only access your own reservations");
        }
    }

    private void processReservation(
        Long seatId,
        User user,
        LocalDate reservationDate,
        LocalTime startTime,
        LocalTime endTime,
        boolean bookEntireRoom,
        List<Reservation> createdReservations
    ) {
        validateReservationDate(reservationDate);

        var seat = seatRepository.findById(seatId)
                .orElseThrow(SeatNotFoundException::new);

        // Check availability (time-aware for conference/collaborative/recreational rooms)
        validateSeatAvailability(seat, reservationDate, startTime, endTime);

        var reservation = reservationRepository.save(Reservation.builder()
                .reservationDate(reservationDate)
                .startTime(startTime)
                .endTime(endTime)
                .bookEntireRoom(bookEntireRoom)
                .status(ReservationStatus.ACTIVE)
                .seat(seat)
                .user(user)
                .build());

        createdReservations.add(reservation);
    }

    private void validateReservationDate(LocalDate reservationDate) {
        LocalDate today = LocalDate.now();
        if (reservationDate.isBefore(today)) {
            throw new InvalidReservationDateException("Reservation date cannot be in the past");
        }


        LocalDate maxDate = today.plusDays(5);
        if (reservationDate.isAfter(maxDate)) {
            throw new InvalidReservationDateException("Reservations can only be made up to 5 days in advance");
        }
    }

    private void validateSeatAvailability(
        Seat seat,
        LocalDate reservationDate,
        LocalTime startTime,
        LocalTime endTime
    ) {
        RoomType roomType = seat.getRoom().getRoomType();

        if (roomType == RoomType.DESK_ROOM) {
            // DESK_ROOM: Check all-day availability
            if (isSeatAlreadyReserved(seat.getId(), reservationDate)) {
                throw new SeatAlreadyReservedException(
                    "Seat is already reserved for this date"
                );
            }
        } else {
            // CONFERENCE_ROOM, COLLABORATIVE, RECREATIONAL: Check time slot overlap
            boolean hasOverlap = reservationRepository.existsOverlappingReservation(
                seat.getId(),
                reservationDate,
                startTime,
                endTime,
                ReservationStatus.ACTIVE
            );

            if (hasOverlap) {
                throw new SeatAlreadyReservedException(
                    "Seat is already reserved for overlapping time slot"
                );
            }
        }
    }

    private boolean isSeatAlreadyReserved(Long seatId, LocalDate reservationDate) {
        return reservationRepository.existsBySeatIdAndReservationDateAndStatus(
            seatId,
            reservationDate,
            ReservationStatus.ACTIVE
        );
    }

    @Override
    @Transactional(readOnly = true)
    public CreateReservationResponse getAllReservations(Jwt jwt) {
        var currentUser = userService.findUser(jwt);
        var reservations = reservationRepository.findAll().stream()
                .filter(r -> r.getUser().getId().equals(currentUser.getId()))
                .toList();

        reservations.forEach(this::eagerlyFetchNestedEntities);

        var reservationDTOs = reservations.stream()
                .map(reservation -> {
                    var dto = reservationMapper.toDTO(reservation);
                    enrichWithWeather(dto, reservation);
                    enrichWithTraffic(dto, reservation);
                    return dto;
                })
                .toList();

        return CreateReservationResponse.builder()
                .reservations(reservationDTOs)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public CreateReservationResponse getReservation(Long reservationId, Jwt jwt) {
        var currentUser = userService.findUser(jwt);
        var reservation = reservationRepository.findById(reservationId)
                .orElseThrow(ReservationNotFoundException::new);

        verifyOwnership(reservation, currentUser);

        eagerlyFetchNestedEntities(reservation);

        var dto = reservationMapper.toDTO(reservation);
        enrichWithWeather(dto, reservation);
        enrichWithTraffic(dto, reservation);

        return CreateReservationResponse.builder()
                .reservations(List.of(dto))
                .build();
    }

    @Override
    @Transactional
    public void deleteReservation(Long reservationId, Jwt jwt) {
        var currentUser = userService.getOrCreateUser(jwt);
        var reservation = reservationRepository.findById(reservationId)
                .orElseThrow(ReservationNotFoundException::new);

        verifyOwnership(reservation, currentUser);

        reservation.setStatus(ReservationStatus.CANCELLED);
        reservationRepository.save(reservation);
        log.info("Cancelled reservation with ID: {}", reservationId);
    }

    @Override
    @Transactional
    public CreateReservationResponse updateReservation(Long reservationId, org.itss.backtoschool.deskops.dto.request.UpdateReservationRequest request, Jwt jwt) {
        var currentUser = userService.getOrCreateUser(jwt);
        var reservation = reservationRepository.findById(reservationId)
                .orElseThrow(ReservationNotFoundException::new);

        verifyOwnership(reservation, currentUser);

        boolean seatChanged = request.getSeatId() != null && !request.getSeatId().equals(reservation.getSeat().getId());
        boolean dateChanged = request.getReservationDate() != null && !request.getReservationDate().equals(reservation.getReservationDate());
        boolean timeChanged = request.getStartTime() != null || request.getEndTime() != null;

        if (dateChanged) {
            validateReservationDate(request.getReservationDate());
        }

        // Determine target date and times for validation
        LocalDate targetDate = request.getReservationDate() != null ? request.getReservationDate() : reservation.getReservationDate();
        LocalTime targetStartTime = request.getStartTime() != null ? request.getStartTime() : reservation.getStartTime();
        LocalTime targetEndTime = request.getEndTime() != null ? request.getEndTime() : reservation.getEndTime();

        if (seatChanged) {
            var newSeat = seatRepository.findById(request.getSeatId())
                    .orElseThrow(SeatNotFoundException::new);
            validateSeatAvailability(newSeat, targetDate, targetStartTime, targetEndTime);
            reservation.setSeat(newSeat);
        }

        if (dateChanged || timeChanged) {
            validateSeatAvailability(reservation.getSeat(), targetDate, targetStartTime, targetEndTime);
            if (dateChanged) {
                reservation.setReservationDate(request.getReservationDate());
            }
            if (request.getStartTime() != null) {
                reservation.setStartTime(request.getStartTime());
            }
            if (request.getEndTime() != null) {
                reservation.setEndTime(request.getEndTime());
            }
        }

        reservationRepository.save(reservation);
        log.info("Updated reservation with ID: {}", reservationId);

        eagerlyFetchNestedEntities(reservation);

        var dto = reservationMapper.toDTO(reservation);
        enrichWithWeather(dto, reservation);
        enrichWithTraffic(dto, reservation);

        return CreateReservationResponse.builder()
                .reservations(List.of(dto))
                .build();
    }
}
