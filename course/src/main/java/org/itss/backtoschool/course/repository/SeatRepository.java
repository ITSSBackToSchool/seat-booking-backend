package org.itss.backtoschool.course.repository;

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
public interface SeatRepository extends JpaRepository<Seat, Long> {
	@Query("SELECT s FROM Seat s " +
			"WHERE s.id NOT IN (" +
			"   SELECT r.seat.id FROM Reservation r " +
			"   WHERE r.reservationDateStart = :dateStart AND r.reservationDateEnd = :dateEnd AND r.status = :status)")
	List<Seat> findAvailableSeats(@Param("dateStart") LocalDateTime dateStart, @Param("dateEnd") LocalDateTime dateEnd, @Param("status") ReservationStatus status);
}
