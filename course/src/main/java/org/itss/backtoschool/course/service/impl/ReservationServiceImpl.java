package org.itss.backtoschool.course.service.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.dto.ReservationRoomDTO;
import org.itss.backtoschool.course.dto.ReservationSeatDTO;
import org.itss.backtoschool.course.dto.request.CreateReservationRoomRequest;
import org.itss.backtoschool.course.dto.request.CreateReservationSeatRequest;
import org.itss.backtoschool.course.dto.response.CreateReservationRoomResponse;
import org.itss.backtoschool.course.dto.response.CreateReservationSeatResponse;
import org.itss.backtoschool.course.entities.*;
import org.itss.backtoschool.course.mapper.ReservationRoomMapper;
import org.itss.backtoschool.course.mapper.ReservationSeatMapper;
import org.itss.backtoschool.course.repository.ReservationRepository;
import org.itss.backtoschool.course.repository.RoomRepository;
import org.itss.backtoschool.course.repository.SeatRepository;
import org.itss.backtoschool.course.repository.UserRepository;
import org.itss.backtoschool.course.service.ReservationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final SeatRepository seatRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final ReservationSeatMapper reservationSeatMapper;
    private final ReservationRoomMapper reservationRoomMapper;



    @Override
    @Transactional
    public CreateReservationSeatResponse createReservationsForSeats(CreateReservationSeatRequest request) {
        User user = loadUser(request.getUserId());

        List<Reservation> createdReservations = new ArrayList<>();
        LocalDate date = request.getReservationDate();
        LocalTime startTime = request.getStartTime() != null ? request.getStartTime() : LocalTime.of(9, 0);
        LocalTime endTime = request.getEndTime() != null ? request.getEndTime() : startTime.plusHours(1);

        for (Long seatId : request.getSeatIds()) {
            createdReservations.add(createReservationForSeat(seatId, user, date, startTime, endTime));
        }

        List<ReservationSeatDTO> dtos = createdReservations.stream()
                .map(reservationSeatMapper::toDTO)
                .toList();

        return CreateReservationSeatResponse.builder()
                .reservations(dtos)
                .build();
    }


    private Reservation createReservationForSeat(Long seatId, User user,
                                                 LocalDate date, LocalTime startTime, LocalTime endTime) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found with id=" + seatId));

        if (reservationRepository.existsBySeat_IdAndReservationDateAndStartTimeAndStatus(
                seatId, date, startTime, ReservationStatus.ACTIVE)) {
            throw new RuntimeException("Seat " + seatId + " already reserved for " + date + " at " + startTime);
        }

        return reservationRepository.save(
                Reservation.builder()
                        .reservationDate(date)
                        .startTime(startTime)
                        .endTime(endTime)
                        .status(ReservationStatus.ACTIVE)
                        .seat(seat)
                        .room(seat.getRoom())
                        .users(user)
                        .build()
        );
    }


    @Override
    @Transactional
    public CreateReservationRoomResponse createReservationsForRooms(CreateReservationRoomRequest request) {
        User user = loadUser(request.getUserId());

        List<Reservation> createdReservations = new ArrayList<>();
        LocalDate date = request.getReservationDate();
        LocalTime startTime = request.getStartTime() != null ? request.getStartTime() : LocalTime.of(9, 0);
        LocalTime endTime = request.getEndTime() != null ? request.getEndTime() : startTime.plusHours(1);

        for (Long roomId : request.getRoomIds()) {
            createdReservations.add(createReservationForRoom(roomId, user, date, startTime, endTime));
        }

        List<ReservationRoomDTO> dtos = createdReservations.stream()
                .map(reservationRoomMapper::toDTO)
                .toList();

        return CreateReservationRoomResponse.builder()
                .reservations(dtos)
                .build();
    }

    private Reservation createReservationForRoom(Long roomId, User user,
                                                 LocalDate date, LocalTime startTime, LocalTime endTime) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with id=" + roomId));

        if (reservationRepository.existsByRoom_IdAndReservationDateAndStartTimeAndStatus(
                roomId, date, startTime, ReservationStatus.ACTIVE)) {
            throw new RuntimeException("Room " + roomId + " already reserved for " + date + " at " + startTime);
        }

        return reservationRepository.save(
                Reservation.builder()
                        .reservationDate(date)
                        .startTime(startTime)
                        .endTime(endTime)
                        .status(ReservationStatus.ACTIVE)
                        .room(room)
                        .users(user)
                        .build()
        );
    }



    private User loadUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id=" + userId));
    }
}
