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

    boolean existsByRoom_IdAndReservationDateAndStartTimeAndStatus(
            Long roomId,
            LocalDate reservationDate,
            LocalTime startTime,
            ReservationStatus status
    );
    
    // Find all reservations for a seat on a specific date
    List<Reservation> findBySeatIdAndReservationDate(Long seatId, LocalDate reservationDate);
    
    // Check if seat has overlapping reservation
    @Query("SELECT r FROM Reservation r WHERE r.seat.id = :seatId " +
           "AND r.reservationDate = :date " +
           "AND r.status = 'ACTIVE' " +
           "AND ((r.startTime < :endTime AND r.endTime > :startTime))")
    List<Reservation> findOverlappingReservations(
            @Param("seatId") Long seatId,
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
    );
    
    // Find reservations by user
    List<Reservation> findByUsersIdOrderByReservationDateDesc(Long userId);
    
    // Find all room reservations with eager loading
    @Query("SELECT r FROM Reservation r " +
           "LEFT JOIN FETCH r.room room " +
           "LEFT JOIN FETCH room.floor floor " +
           "LEFT JOIN FETCH floor.building " +
           "LEFT JOIN FETCH r.users " +
           "WHERE r.seat IS NULL AND r.room IS NOT NULL")
    List<Reservation> findAllRoomReservationsWithDetails();
}
