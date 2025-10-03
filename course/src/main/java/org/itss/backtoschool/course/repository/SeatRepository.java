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
	@Query("SELECT s FROM Seat s " +
			"WHERE s.room.floor.id = :floorId " +
			"AND s.room.floor.building.name = :buildingName " +
			"AND s.room.roomType = 'DESK_ROOM' " +
			"AND s.id NOT IN (" +
			"   SELECT r.seat.id FROM Reservation r " +
			"   WHERE r.reservationDateStart = :dateStart " +
			"   AND r.reservationDateEnd = :dateEnd" +
			"   AND r.status='ACTIVE'" +
			")")
	List<Seat> findAvailableSeatsByRoomAndFloor(
			@Param("floorId") Long floorId,
			@Param("buildingName") String buildingName,
			@Param("dateStart") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateStart,
			@Param("dateEnd") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateEnd);

}
