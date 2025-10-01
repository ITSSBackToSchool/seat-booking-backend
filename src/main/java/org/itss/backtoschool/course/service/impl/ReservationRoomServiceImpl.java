package org.itss.backtoschool.course.service.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.dto.ReservationRoomDTO;
import org.itss.backtoschool.course.dto.request.CreateReservationRoomRequest;
import org.itss.backtoschool.course.dto.response.CreateReservationRoomResponse;
import org.itss.backtoschool.course.entities.*;
import org.itss.backtoschool.course.mapper.ReservationRoomMapper;
import org.itss.backtoschool.course.repository.ReservationRepository;
import org.itss.backtoschool.course.repository.RoomRepository;
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
public class ReservationRoomServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final ReservationRoomMapper reservationRoomMapper;

    @Override
    @Transactional
    public CreateReservationRoomResponse createReservations(CreateReservationRoomRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Reservation> createdReservations = new ArrayList<>();

        LocalDate date = request.getReservationDate();
        LocalTime time = request.getReservationTime();

        for (Long roomId : request.getRoomIds()) {
            Reservation reservation = createReservationForRoom(roomId, user, date, time);
            createdReservations.add(reservation);
        }

        List<ReservationRoomDTO> dtos = createdReservations.stream()
                .map(reservationRoomMapper::toDTO)
                .toList();

        return CreateReservationRoomResponse.builder()
                .reservations(dtos)
                .build();
    }

    private Reservation createReservationForRoom(Long roomId, User user,
                                                 LocalDate date, LocalTime time) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));


        validateRoomAvailability(room.getId(), date, time);

        Reservation reservation = Reservation.builder()
                .reservationDate(date)
                .startTime(time)
                .endTime(time)
                .status(ReservationStatus.ACTIVE)
                .room(room)
                .users(user)
                .build();

        return reservationRepository.save(reservation);
    }


    private void validateRoomAvailability(Long roomId, LocalDate date, LocalTime time) {
        boolean exists = reservationRepository.existsByRoom_IdAndReservationDateAndStartTimeAndStatus(
                roomId, date, time, ReservationStatus.ACTIVE
        );

        if (exists) {
            throw new RuntimeException("Room already reserved for this date and time");
        }}

    private void processRoomReservation(Long roomId, User user,
                                        LocalDate date, LocalTime time,
                                        List<Reservation> sink) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));


        validateRoomAvailability(room.getId(), date, time);


        Reservation saved = reservationRepository.save(
                Reservation.builder()
                        .reservationDate(date)
                        .startTime(time)
                        .endTime(time)
                        .status(ReservationStatus.ACTIVE)
                        .room(room)
                        .users(user)
                        .build()
        );


        sink.add(saved);

    }
}