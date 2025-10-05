package org.itss.backtoschool.course.controller;

import org.itss.backtoschool.course.dto.response.TimeSlot;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomController {
	@GetMapping("/timeslots")
	List<TimeSlot> findByRoom_IdAndReservationDateStartLessThanAndReservationDateEndGreaterThan(
			@Param("roomId") Long roomId, @Param("dateStart") LocalDateTime dateStart, @Param("dateEnd") LocalDateTime dateEnd
	);
}
