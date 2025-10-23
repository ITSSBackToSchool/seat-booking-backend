package org.itss.backtoschool.course.service.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.dto.ReservationRoomDTO;
import org.itss.backtoschool.course.dto.ReservationSeatDTO;
import org.itss.backtoschool.course.dto.UserReservationDTO;
import org.itss.backtoschool.course.dto.request.CreateReservationRoomRequest;
import org.itss.backtoschool.course.dto.request.CreateReservationSeatRequest;
import org.itss.backtoschool.course.dto.request.UpdateReservationRequest;
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
    LocalDate date = request.getReservationDate();

    LocalTime startTime = normalizeTime(request.getStartTime(), LocalTime.of(9, 0));
    LocalTime endTime = normalizeTime(request.getEndTime(), startTime.plusHours(1));

    List<Reservation> createdReservations = new ArrayList<>();

    for (Long seatId : request.getSeatId()) {
      createdReservations.add(createReservationForSeat(seatId, user, date, startTime, endTime));
    }

    List<ReservationSeatDTO> dtos = createdReservations.stream()
      .map(reservationSeatMapper::toDTO)
      .toList();

    return CreateReservationSeatResponse.builder()
      .reservations(dtos)
      .build();
  }

  private Reservation createReservationForSeat(Long seatId, User user, LocalDate date,
                                               LocalTime startTime, LocalTime endTime) {

    Seat seat = seatRepository.findById(seatId)
      .orElseThrow(() -> new RuntimeException("Seat not found with id=" + seatId));

    boolean overlap = !reservationRepository.findOverlappingSeatReservations(seatId, date, startTime, endTime).isEmpty();
    if (overlap) {
      throw new RuntimeException("Seat " + seatId + " is already booked in this interval!");
    }

    Reservation saved = reservationRepository.save(
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

    System.out.printf("✅ Seat reservation saved (id=%d) for user=%s, seat=%s%n",
      saved.getId(), user.getUserName(), seat.getSeatNumber());

    return saved;
  }





  @Override
  @Transactional
  public CreateReservationRoomResponse createReservationForRoom(CreateReservationRoomRequest request) {
    System.out.println("🎯 Starting room reservation creation...");

    User user = loadUser(request.getUserId());
    Long roomId = request.getRoomIds();
    if (roomId == null) {
      throw new IllegalArgumentException("Room ID must be provided for reservation");
    }

    LocalDate date = request.getReservationDate();
    LocalTime startTime = normalizeTime(request.getStartTime(), LocalTime.of(9, 0));
    LocalTime endTime = normalizeTime(request.getEndTime(), startTime.plusHours(1));

    System.out.printf("🔹 Checking room reservation → roomId=%d, date=%s, start=%s, end=%s%n",
      roomId, date, startTime, endTime);

    boolean overlap = reservationRepository.hasOverlappingRoomReservation(roomId, date, startTime, endTime);
    if (overlap) {
      System.out.println("⚠️ Room already booked in this interval!");
      throw new RuntimeException("Room " + roomId + " is already booked in this interval!");
    }

    Room room = roomRepository.findById(roomId)
      .orElseThrow(() -> new RuntimeException("Room not found with id=" + roomId));

    Reservation saved = reservationRepository.save(
      Reservation.builder()
        .reservationDate(date)
        .startTime(startTime)
        .endTime(endTime)
        .status(ReservationStatus.ACTIVE)
        .room(room)
        .users(user)
        .build()
    );

    System.out.printf("✅ Room reservation created (id=%d) for user=%s, room=%s%n",
      saved.getId(), user.getUserName(), room.getName());

    ReservationRoomDTO dto = reservationRoomMapper.toDTO(saved);

    return CreateReservationRoomResponse.builder()
      .reservation(dto)
      .build();
  }





  @Override
  @Transactional(readOnly = true)
  public List<UserReservationDTO> getUserReservations(Long userId) {
    List<Reservation> reservations = reservationRepository.findByUsersIdOrderByReservationDateDesc(userId);

    return reservations.stream()
      .map(r -> UserReservationDTO.builder()
        .id(r.getId())
        .seatNumber(r.getSeat() != null ? r.getSeat().getSeatNumber() : null)
        .roomName(r.getRoom() != null ? r.getRoom().getName() : null)
        .floorName(r.getRoom() != null ? r.getRoom().getFloor().getName() : null)
        .buildingName(r.getRoom() != null ? r.getRoom().getFloor().getBuilding().getName() : null)
        .reservationDate(r.getReservationDate())
        .startTime(r.getStartTime())
        .endTime(r.getEndTime())
        .status(r.getStatus().name())
        .build())
      .toList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<ReservationSeatDTO> getAllSeatReservations() {
    return reservationRepository.findAll().stream()
      .filter(r -> r.getSeat() != null)
      .map(reservationSeatMapper::toDTO)
      .toList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<ReservationRoomDTO> getAllRoomReservations() {
    return reservationRepository.findAllRoomReservationsWithDetails().stream()
      .map(reservationRoomMapper::toDTO)
      .toList();
  }





  @Override
  @Transactional
  public UserReservationDTO updateReservation(Long reservationId, UpdateReservationRequest request) {
    Reservation reservation = reservationRepository.findById(reservationId)
      .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + reservationId));


    if (request.getReservationDate() != null) {
      reservation.setReservationDate(request.getReservationDate());
    }
    if (request.getStartTime() != null) {
      reservation.setStartTime(normalizeTime(request.getStartTime(), LocalTime.of(9, 0)));
    }
    if (request.getEndTime() != null) {
      reservation.setEndTime(normalizeTime(request.getEndTime(), LocalTime.of(17, 0)));
    }

    reservationRepository.save(reservation);

    System.out.printf("✏️ Reservation updated (id=%d)%n", reservationId);


    return UserReservationDTO.builder()
      .id(reservation.getId())
      .seatNumber(reservation.getSeat() != null ? reservation.getSeat().getSeatNumber() : null)
      .roomName(reservation.getRoom() != null ? reservation.getRoom().getName() : null)
      .floorName(reservation.getRoom() != null ? reservation.getRoom().getFloor().getName() : null)
      .buildingName(reservation.getRoom() != null ? reservation.getRoom().getFloor().getBuilding().getName() : null)
      .reservationDate(reservation.getReservationDate())
      .startTime(reservation.getStartTime())
      .endTime(reservation.getEndTime())
      .status(reservation.getStatus().name())
      .build();
  }

  @Override
  @Transactional
  public void cancelReservation(Long reservationId) {
    Reservation reservation = reservationRepository.findById(reservationId)
      .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + reservationId));

    reservation.setStatus(ReservationStatus.CANCELLED);
    reservationRepository.save(reservation);

    System.out.printf("🚫 Reservation cancelled (id=%d)%n", reservationId);
  }

  private User loadUser(Long userId) {
    return userRepository.findById(userId)
      .orElseThrow(() -> new RuntimeException("User not found with id=" + userId));
  }

  private LocalTime normalizeTime(LocalTime time, LocalTime fallback) {
    return (time != null ? time : fallback).withSecond(0).withNano(0);
  }
}
