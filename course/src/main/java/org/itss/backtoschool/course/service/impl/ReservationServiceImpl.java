package org.itss.backtoschool.course.service.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.dto.request.CreateReservationRequest;
import org.itss.backtoschool.course.dto.response.CreateReservationResponse;
import org.itss.backtoschool.course.entities.Reservation;
import org.itss.backtoschool.course.entities.ReservationStatus;
import org.itss.backtoschool.course.entities.Seat;
import org.itss.backtoschool.course.entities.User;
import org.itss.backtoschool.course.exception.seat.SeatAlreadyReservedException;
import org.itss.backtoschool.course.exception.seat.SeatNotFoundException;
import org.itss.backtoschool.course.exception.user.UserNotFoundException;
import org.itss.backtoschool.course.mapper.ReservationMapper;
import org.itss.backtoschool.course.repository.ReservationRepository;
import org.itss.backtoschool.course.repository.SeatRepository;
import org.itss.backtoschool.course.repository.UserRepository;
import org.itss.backtoschool.course.service.ReservationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
    private final ReservationMapper reservationMapper;


    @Override
    @Transactional
    public CreateReservationResponse createReservations(CreateReservationRequest request) {
        var user = loadUser(request.getUserId());
        var createdReservations = new ArrayList<Reservation>();

        request.getSeatIds().forEach(seatId ->
                processReservation(seatId, user, request.getReservationDate(), createdReservations)
        );

        var reservationDTOs = createdReservations.stream()
                .map(reservationMapper::toDTO)
                .toList();

        return CreateReservationResponse.builder()
                .reservations(reservationDTOs)
                .build();
    }

    private User loadUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    private void processReservation(Long seatId, User user, LocalDate reservationDate, List<Reservation> createdReservations) {

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

    private void validateSeatAvailability(Seat seat, LocalDate reservationDate) {
        if (isSeatAlreadyReserved(seat.getId(), reservationDate)) {
            throw new SeatAlreadyReservedException();
        }
    }

    private boolean isSeatAlreadyReserved(Long seatId, LocalDate reservationDate) {
        return reservationRepository.existsBySeatIdAndReservationDateAndStatus(seatId, reservationDate, ReservationStatus.ACTIVE);
    }
}
