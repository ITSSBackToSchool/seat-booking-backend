package org.itss.backtoschool.deskops.repository;

import org.itss.backtoschool.deskops.entities.Reservation;
import org.itss.backtoschool.deskops.entities.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsBySeatIdAndReservationDateAndStatus(Long seatId, LocalDate reservationDate, ReservationStatus reservationStatus);
}
