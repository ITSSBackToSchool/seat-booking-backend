package org.itss.backtoschool.deskops.repository;

import org.itss.backtoschool.deskops.entities.Reservation;
import org.itss.backtoschool.deskops.entities.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsBySeatIdAndReservationDateAndStatus(Long seatId, LocalDate reservationDate, ReservationStatus reservationStatus);

    /**
     * Find all reservations with a specific status before a given date (exclusive).
     * Used by scheduler to mark past reservations as COMPLETED.
     */
    List<Reservation> findAllByReservationDateLessThanAndStatus(LocalDate date, ReservationStatus status);
}
