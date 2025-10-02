package org.itss.backtoschool.course.service.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.dto.ReservationDTO;
import org.itss.backtoschool.course.dto.request.CreateReservationRequest;
import org.itss.backtoschool.course.dto.response.CreateReservationResponse;
import org.itss.backtoschool.course.entities.Reservation;
import org.itss.backtoschool.course.entities.ReservationStatus;
import org.itss.backtoschool.course.entities.Seat;
import org.itss.backtoschool.course.entities.User;
import org.itss.backtoschool.course.mapper.ReservationMapper;
import org.itss.backtoschool.course.repository.ReservationRepository;
import org.itss.backtoschool.course.repository.SeatRepository;
import org.itss.backtoschool.course.repository.UserRepository;
import org.itss.backtoschool.course.service.ReservationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
                processReservation(seatId, user, request.getReservationDateStart(), request.getReservationDateEnd(), createdReservations)
        );

        var reservationDTOs = createdReservations.stream()
                .map(reservationMapper::toDTO)
                .toList();

        return CreateReservationResponse.builder()
                .reservations(reservationDTOs)
                .build();
    }

    @Override
    public List<ReservationDTO> findAll(){
        return reservationRepository.findAll().stream().map(reservationMapper::toDTO).toList();
    }

    @Override
    public List<ReservationDTO> findReservationsByUserId(Long userId) {
        return reservationRepository
                .findReservationsByUserId(userId).stream()
                .map(reservationMapper::toDTO).toList();
    }

    @Override
    public String cancelResevation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(RuntimeException::new);
        if(reservation.getStatus()!=ReservationStatus.CANCELLED) {
            reservation.setStatus(ReservationStatus.CANCELLED);
            reservationRepository.save(reservation);
        }

        return "Rezervare anulata cu succes";
    }

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public String completeReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        reservations.stream()
                .filter(reservation -> reservation.getReservationDateEnd().isBefore(LocalDateTime.now()) && reservation.getStatus()==ReservationStatus.ACTIVE)
                .forEach(reservation -> reservation.setStatus(ReservationStatus.COMPLETED));

        reservationRepository.saveAll(reservations);
        return "Rezervarile completate cu succes";
    }


    private User loadUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(RuntimeException::new);
    }

    private void processReservation(Long seatId, User user, LocalDateTime reservationDateStart, LocalDateTime reservationDateEnd, List<Reservation> createdReservations) {

        var seat = seatRepository.findById(seatId).orElseThrow(RuntimeException::new);
        validateSeatAvailability(seat, reservationDateStart, reservationDateEnd);

        var reservation = reservationRepository.save(Reservation.builder()
                .reservationDateStart(reservationDateStart)
                .reservationDateEnd(reservationDateEnd)
                .status(ReservationStatus.ACTIVE)
                .seat(seat)
                .user(user)
                .build());

        createdReservations.add(reservation);
    }

    private void validateSeatAvailability(Seat seat, LocalDateTime reservationDateStart, LocalDateTime reservationDateEnd) {
        if (isSeatAlreadyReserved(seat.getId(), reservationDateStart, reservationDateEnd)) {
            throw new RuntimeException();
        }
    }

    private boolean isSeatAlreadyReserved(Long seatId, LocalDateTime reservationDateStart, LocalDateTime reservationDateEnd) {
        return reservationRepository.existsBySeatIdAndReservationDateStartAndReservationDateEndAndStatus(seatId, reservationDateStart, reservationDateStart, ReservationStatus.ACTIVE);
    }
}
