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

  // 🔹 Verifică dacă un SEAT este deja rezervat exact în acel moment
  boolean existsBySeat_IdAndReservationDateAndStartTimeAndStatus(
    Long seatId,
    LocalDate reservationDate,
    LocalTime startTime,
    ReservationStatus status
  );

  // 🔹 Verifică dacă o cameră este rezervată exact la acea oră
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

  // 🔹 Verifică dacă o cameră are rezervări care se suprapun în intervalul dat
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

  // 🔹 Pentru scaune — rezervări suprapuse (folosit la validare)
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

  // 🔹 Toate rezervările unui utilizator (pentru Dashboard)
  List<Reservation> findByUsersIdOrderByReservationDateDesc(Long userId);

  // 🔹 Toate rezervările unui seat într-o anumită zi
  List<Reservation> findBySeatIdAndReservationDate(Long seatId, LocalDate reservationDate);

  // 🔹 Toate rezervările de camere cu detalii JOIN (room + floor + building + user)
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
