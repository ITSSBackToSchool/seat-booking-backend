package org.itss.backtoschool.course.repository;

import org.itss.backtoschool.course.entities.Reservation;
import org.itss.backtoschool.course.entities.ReservationStatus;
import org.itss.backtoschool.course.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsBySeatIdAndReservationDateStartAndReservationDateEndAndStatus(Long seatId, LocalDateTime reservationDateStart, LocalDateTime reservationDateEnd, ReservationStatus reservationStatus);
    List<Reservation> findReservationsByUserId(Long userId);
    boolean existsByRoomIdAndStatusAndReservationDateStartLessThanAndReservationDateEndGreaterThan(
            Long roomId,
            ReservationStatus status,
            LocalDateTime reservationDateEnd,
            LocalDateTime reservationDateStart
    );

    List<Reservation> findByRoom_IdAndStatusAndReservationDateStartLessThanAndReservationDateEndGreaterThan(
            Long roomId,  ReservationStatus status, LocalDateTime dateStart, LocalDateTime dateEnd
    );
    List<Reservation> findReservationsByUser_IdAndStatus(Long userId, ReservationStatus status);
}
