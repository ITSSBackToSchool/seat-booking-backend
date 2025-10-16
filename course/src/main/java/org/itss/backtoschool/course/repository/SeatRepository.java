package org.itss.backtoschool.course.repository;


import org.itss.backtoschool.course.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

	@Query(value = """
    SELECT s FROM Seat s
    WHERE s.floor.id = :floorId
    AND s.id NOT IN (
        SELECT r.seat.id FROM Reservation r
        WHERE r.status = 'ACTIVE'
        AND r.seat IS NOT NULL
        AND (
            (:dateStart >= r.reservationDateStart AND :dateStart <= r.reservationDateEnd)
            OR (:dateEnd >= r.reservationDateStart AND :dateEnd <= r.reservationDateEnd)
            OR (r.reservationDateStart >= :dateStart AND r.reservationDateStart <= :dateEnd)
            OR (r.reservationDateEnd >= :dateStart AND r.reservationDateEnd <= :dateEnd)
        )
    )
""")
	List<Seat> findAvailableSeatsByFloorAndReservationTime(
			@Param("floorId") Long floorId,
			@Param("dateStart") LocalDateTime dateStart,
			@Param("dateEnd") LocalDateTime dateEnd
	);

	List<Seat> findSeatsByFloor_Id(Long floorId);



}
