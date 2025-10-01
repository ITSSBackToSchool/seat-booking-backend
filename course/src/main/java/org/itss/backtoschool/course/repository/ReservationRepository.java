package org.itss.backtoschool.course.repository;

import org.itss.backtoschool.course.entities.Reservation;
import org.itss.backtoschool.course.entities.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

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
}
