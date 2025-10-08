package org.itss.backtoschool.deskops.repository;

import org.itss.backtoschool.deskops.entities.Reservation;
import org.itss.backtoschool.deskops.entities.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    /**
     * Check if a seat is already reserved for all-day booking (DESK_ROOM).
     * Used for desk rooms that don't have time slots.
     */
    boolean existsBySeatIdAndReservationDateAndStatus(
        Long seatId,
        LocalDate reservationDate,
        ReservationStatus reservationStatus
    );

    /**
     * Check if a seat has overlapping time slot reservations.
     * Used for CONFERENCE_ROOM, COLLABORATIVE, and RECREATIONAL rooms.
     * Overlap logic: (newStart < existingEnd) AND (newEnd > existingStart)
     */
    @Query("""
        SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END
        FROM Reservation r
        WHERE r.seat.id = :seatId
        AND r.reservationDate = :date
        AND r.status = :status
        AND r.startTime IS NOT NULL
        AND r.endTime IS NOT NULL
        AND (r.startTime < :endTime AND r.endTime > :startTime)
    """)
    boolean existsOverlappingReservation(
        @Param("seatId") Long seatId,
        @Param("date") LocalDate date,
        @Param("startTime") LocalTime startTime,
        @Param("endTime") LocalTime endTime,
        @Param("status") ReservationStatus status
    );

    /**
     * Get all reservations for a specific room on a specific date.
     * Useful for displaying room availability timeline.
     */
    @Query("""
        SELECT r FROM Reservation r
        JOIN FETCH r.seat s
        WHERE s.room.id = :roomId
        AND r.reservationDate = :date
        AND r.status = :status
        ORDER BY r.startTime ASC
    """)
    List<Reservation> findByRoomAndDateAndStatus(
        @Param("roomId") Long roomId,
        @Param("date") LocalDate date,
        @Param("status") ReservationStatus status
    );

    /**
     * Find all reservations with a specific status before a given date (exclusive).
     * Used by scheduler to mark past reservations as COMPLETED.
     */
    List<Reservation> findAllByReservationDateLessThanAndStatus(
        LocalDate date,
        ReservationStatus status
    );

    /**
     * Find all reservations for a specific date and status.
     * Used by scheduler to check if time-based reservations have ended.
     */
    List<Reservation> findAllByReservationDateAndStatus(
        LocalDate date,
        ReservationStatus status
    );
}
