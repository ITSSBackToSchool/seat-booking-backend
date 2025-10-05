package org.itss.backtoschool.course.service;

import org.itss.backtoschool.course.dto.RoomDTO;
import org.itss.backtoschool.course.dto.response.TimeSlot;
import org.itss.backtoschool.course.entities.Room;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomService {
	List<TimeSlot> findByRoom_IdAndReservationDateStartLessThanAndReservationDateEndGreaterThan(
			Long roomId, LocalDateTime dateEnd, LocalDateTime dateStart
	);

	List<RoomDTO> findAllByFloorNameAndFloor_Building_Name(String floorName, String buildingName);
}
