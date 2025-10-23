package org.itss.backtoschool.course.repository;

import org.itss.backtoschool.course.entities.Reservation;
import org.itss.backtoschool.course.entities.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {


  boolean existsBySeat_IdAndReservationDateAndStartTimeAndStatus(
    Long seatId,
    LocalDate reservationDate,
    LocalTime startTime,
    ReservationStatus status
  );


  @Query("""
           SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END
           FROM Reservation r
           WHERE r.room.id = :roomId
             AND r.reservationDate = :date
             AND r.startTime = :startTime
             AND r.status = org.itss.backtoschool.course.entities.ReservationStatus.ACTIVE
           """)
  boolean roomAlreadyReserved(
    @Param("roomId") Long roomId,
    @Param("date") LocalDate date,
    @Param("startTime") LocalTime startTime
  );


  @Query("""
           SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END
           FROM Reservation r
           WHERE r.room.id = :roomId
             AND r.reservationDate = :date
             AND r.status = org.itss.backtoschool.course.entities.ReservationStatus.ACTIVE
             AND (r.startTime < :endTime AND r.endTime > :startTime)
           """)
  boolean hasOverlappingRoomReservation(
    @Param("roomId") Long roomId,
    @Param("date") LocalDate date,
    @Param("startTime") LocalTime startTime,
    @Param("endTime") LocalTime endTime
  );


  @Query("""
           SELECT r FROM Reservation r
           WHERE r.seat.id = :seatId
             AND r.reservationDate = :date
             AND r.status = org.itss.backtoschool.course.entities.ReservationStatus.ACTIVE
             AND (r.startTime < :endTime AND r.endTime > :startTime)
           """)
  List<Reservation> findOverlappingSeatReservations(
    @Param("seatId") Long seatId,
    @Param("date") LocalDate date,
    @Param("startTime") LocalTime startTime,
    @Param("endTime") LocalTime endTime
  );


  @Query("SELECT r FROM Reservation r WHERE r.users.id = :userId ORDER BY r.reservationDate DESC")
  List<Reservation> findByUsersIdOrderByReservationDateDesc(@Param("userId") Long userId);


  @Query("SELECT r FROM Reservation r WHERE r.seat.id = :seatId AND r.reservationDate = :reservationDate")
  List<Reservation> findBySeatIdAndReservationDate(@Param("seatId") Long seatId, @Param("reservationDate") LocalDate reservationDate);


  @Query("""
           SELECT r FROM Reservation r
           LEFT JOIN FETCH r.room room
           LEFT JOIN FETCH room.floor floor
           LEFT JOIN FETCH floor.building
           LEFT JOIN FETCH r.users
           WHERE r.seat IS NULL AND r.room IS NOT NULL
           """)
  List<Reservation> findAllRoomReservationsWithDetails();
}
