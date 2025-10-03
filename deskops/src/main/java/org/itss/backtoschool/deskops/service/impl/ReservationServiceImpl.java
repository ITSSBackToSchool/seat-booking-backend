package org.itss.backtoschool.deskops.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.itss.backtoschool.deskops.dto.WeatherDTO;
import org.itss.backtoschool.deskops.dto.request.CreateReservationRequest;
import org.itss.backtoschool.deskops.dto.response.CreateReservationResponse;
import org.itss.backtoschool.deskops.entities.Reservation;
import org.itss.backtoschool.deskops.entities.ReservationStatus;
import org.itss.backtoschool.deskops.entities.Seat;
import org.itss.backtoschool.deskops.entities.User;
import org.itss.backtoschool.deskops.exception.auth.UnauthorizedException;
import org.itss.backtoschool.deskops.exception.reservation.InvalidReservationDateException;
import org.itss.backtoschool.deskops.exception.reservation.ReservationNotFoundException;
import org.itss.backtoschool.deskops.exception.seat.SeatAlreadyReservedException;
import org.itss.backtoschool.deskops.exception.seat.SeatNotFoundException;
import org.itss.backtoschool.deskops.mapper.ReservationMapper;
import org.itss.backtoschool.deskops.repository.ReservationRepository;
import org.itss.backtoschool.deskops.repository.SeatRepository;
import org.itss.backtoschool.deskops.service.ReservationService;
import org.itss.backtoschool.deskops.service.UserService;
import org.itss.backtoschool.deskops.service.WeatherService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final SeatRepository seatRepository;
    private final ReservationMapper reservationMapper;
    private final WeatherService weatherService;
    private final UserService userService;


    @Override
    @Transactional
    public CreateReservationResponse createReservations(CreateReservationRequest request, Jwt jwt) {
        var user = userService.getOrCreateUser(jwt);
        var createdReservations = new ArrayList<Reservation>();

        request.getSeatIds().forEach(seatId ->
                processReservation(seatId, user, request.getReservationDate(), createdReservations)
        );

        // Eagerly fetch nested relationships before transaction ends
        for (Reservation createdReservation : createdReservations) {
            eagerlyFetchNestedEntities(createdReservation);
        }

        // Enrich with weather outside transaction
        var reservationDTOs = createdReservations.stream()
                .map(reservation -> {
                    var dto = reservationMapper.toDTO(reservation);
                    enrichWithWeather(dto, reservation);
                    return dto;
                })
                .toList();

        return CreateReservationResponse.builder()
                .reservations(reservationDTOs)
                .build();
    }

    private void eagerlyFetchNestedEntities(Reservation reservation) {
        // Force lazy loading of nested entities before transaction ends
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

    private void verifyOwnership(Reservation reservation, User currentUser) {
        if (!reservation.getUser().getId().equals(currentUser.getId())) {
            throw new UnauthorizedException("You can only access your own reservations");
        }
    }

    private void processReservation(Long seatId, User user, LocalDate reservationDate, List<Reservation> createdReservations) {
        validateReservationDate(reservationDate);

        var seat = seatRepository.findById(seatId).orElseThrow(SeatNotFoundException::new);
        validateSeatAvailability(seat, reservationDate);

        var reservation = reservationRepository.save(Reservation.builder()
                .reservationDate(reservationDate)
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


        // Limit reservations to 5 days in advance (matching weather forecast availability)
        LocalDate maxDate = today.plusDays(5);
        if (reservationDate.isAfter(maxDate)) {
            throw new InvalidReservationDateException("Reservations can only be made up to 5 days in advance");
        }
    }

    private void validateSeatAvailability(Seat seat, LocalDate reservationDate) {
        if (isSeatAlreadyReserved(seat.getId(), reservationDate)) {
            throw new SeatAlreadyReservedException();
        }
    }

    private boolean isSeatAlreadyReserved(Long seatId, LocalDate reservationDate) {
        return reservationRepository.existsBySeatIdAndReservationDateAndStatus(seatId, reservationDate, ReservationStatus.ACTIVE);
    }

    @Override
    @Transactional(readOnly = true)
    public CreateReservationResponse getAllReservations(Jwt jwt) {
        var currentUser = userService.getOrCreateUser(jwt);
        var reservations = reservationRepository.findAll().stream()
                .filter(r -> r.getUser().getId().equals(currentUser.getId()))
                .toList();

        // Eagerly fetch nested relationships before transaction ends
        reservations.forEach(this::eagerlyFetchNestedEntities);

        // Enrich with weather outside transaction
        var reservationDTOs = reservations.stream()
                .map(reservation -> {
                    var dto = reservationMapper.toDTO(reservation);
                    enrichWithWeather(dto, reservation);
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
        var currentUser = userService.getOrCreateUser(jwt);
        var reservation = reservationRepository.findById(reservationId)
                .orElseThrow(ReservationNotFoundException::new);

        verifyOwnership(reservation, currentUser);

        // Eagerly fetch nested relationships before transaction ends
        eagerlyFetchNestedEntities(reservation);

        // Enrich with weather outside transaction
        var dto = reservationMapper.toDTO(reservation);
        enrichWithWeather(dto, reservation);

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

        // Check if updating seat or date
        boolean seatChanged = request.getSeatId() != null && !request.getSeatId().equals(reservation.getSeat().getId());
        boolean dateChanged = request.getReservationDate() != null && !request.getReservationDate().equals(reservation.getReservationDate());

        if (dateChanged) {
            validateReservationDate(request.getReservationDate());
        }

        if (seatChanged) {
            var newSeat = seatRepository.findById(request.getSeatId())
                    .orElseThrow(SeatNotFoundException::new);
            LocalDate targetDate = request.getReservationDate() != null ? request.getReservationDate() : reservation.getReservationDate();
            validateSeatAvailability(newSeat, targetDate);
            reservation.setSeat(newSeat);
        }

        if (dateChanged) {
            validateSeatAvailability(reservation.getSeat(), request.getReservationDate());
            reservation.setReservationDate(request.getReservationDate());
        }

        reservationRepository.save(reservation);
        log.info("Updated reservation with ID: {}", reservationId);

        // Eagerly fetch nested relationships before transaction ends
        eagerlyFetchNestedEntities(reservation);

        // Enrich with weather outside transaction
        var dto = reservationMapper.toDTO(reservation);
        enrichWithWeather(dto, reservation);

        return CreateReservationResponse.builder()
                .reservations(List.of(dto))
                .build();
    }
}
