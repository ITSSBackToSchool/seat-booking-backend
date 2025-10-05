package org.itss.backtoschool.course.service;

import org.itss.backtoschool.course.dto.response.TimeSlot;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomService {
	List<TimeSlot> findByRoom_IdAndReservationDateStartLessThanAndReservationDateEndGreaterThan(
			Long roomId, LocalDateTime dateEnd, LocalDateTime dateStart
	);
}
