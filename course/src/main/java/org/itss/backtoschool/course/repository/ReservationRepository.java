package org.itss.backtoschool.course.repository;

import org.itss.backtoschool.course.entities.Reservation;
import org.itss.backtoschool.course.entities.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsBySeatIdAndReservationDateAndStatus(Long seatId, LocalDate reservationDate, ReservationStatus reservationStatus);
}
