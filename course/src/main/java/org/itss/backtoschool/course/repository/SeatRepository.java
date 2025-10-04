package org.itss.backtoschool.course.repository;


import org.itss.backtoschool.course.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
	@Query("""
        SELECT s FROM Seat s
        WHERE s.floor.id = :floorId
          AND s.floor.building.name = :buildingName
          AND s.id NOT IN (
              SELECT r.seat.id FROM Reservation r
              WHERE r.status = 'ACTIVE'
                AND (r.reservationDateStart < :dateEnd AND r.reservationDateEnd > :dateStart)
          )
    """)
	List<Seat> findAvailableSeatsByFloorAndBuilding(
			@Param("floorId") Long floorId,
			@Param("buildingName") String buildingName,
			@Param("dateStart") LocalDateTime dateStart,
			@Param("dateEnd") LocalDateTime dateEnd
	);
}
