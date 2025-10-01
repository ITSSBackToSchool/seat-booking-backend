package org.itss.backtoschool.course.repository;

import org.itss.backtoschool.course.entities.Reservation;
import org.itss.backtoschool.course.entities.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    //    boolean existsBySeatIdAndReservationDateAndStatus(
//            Long seatId,
//            LocalDate reservationDate,
//            ReservationStatus status
//    );
    boolean existsByRoom_IdAndReservationDateAndStartTimeAndStatus(
            Long roomId,
            LocalDate reservationDate,
            LocalTime startTime,
            ReservationStatus status
    );

}

