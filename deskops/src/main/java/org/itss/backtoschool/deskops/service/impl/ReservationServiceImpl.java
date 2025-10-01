package org.itss.backtoschool.deskops.service.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.deskops.dto.request.CreateReservationRequest;
import org.itss.backtoschool.deskops.dto.response.CreateReservationResponse;
import org.itss.backtoschool.deskops.entities.Reservation;
import org.itss.backtoschool.deskops.entities.ReservationStatus;
import org.itss.backtoschool.deskops.entities.Seat;
import org.itss.backtoschool.deskops.entities.User;
import org.itss.backtoschool.deskops.mapper.ReservationMapper;
import org.itss.backtoschool.deskops.repository.ReservationRepository;
import org.itss.backtoschool.deskops.repository.SeatRepository;
import org.itss.backtoschool.deskops.repository.UserRepository;
import org.itss.backtoschool.deskops.service.ReservationService;
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
        return userRepository.findById(userId).orElseThrow(RuntimeException::new);
    }

    private void processReservation(Long seatId, User user, LocalDate reservationDate, List<Reservation> createdReservations) {

        var seat = seatRepository.findById(seatId).orElseThrow(RuntimeException::new);
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
            throw new RuntimeException();
        }
    }

    private boolean isSeatAlreadyReserved(Long seatId, LocalDate reservationDate) {
        return reservationRepository.existsBySeatIdAndReservationDateAndStatus(seatId, reservationDate, ReservationStatus.ACTIVE);
    }
}
