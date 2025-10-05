package org.itss.backtoschool.course.controller.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.controller.RoomController;
import org.itss.backtoschool.course.dto.RoomDTO;
import org.itss.backtoschool.course.dto.response.TimeSlot;
import org.itss.backtoschool.course.service.ReservationService;
import org.itss.backtoschool.course.service.RoomService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomControllerImpl implements RoomController {
	private final RoomService roomService;
	@Override
	public List<TimeSlot> findByRoom_IdAndReservationDateStartLessThanAndReservationDateEndGreaterThan(Long roomId, LocalDateTime dateStart, LocalDateTime dateEnd) {
		return roomService.findByRoom_IdAndReservationDateStartLessThanAndReservationDateEndGreaterThan(roomId,dateStart,dateEnd);
	}

	@Override
	public List<RoomDTO> findRoomsByFloorNameAndBuildingName(String floorName, String buildingName) {
		return roomService.findAllByFloorNameAndFloor_Building_Name(floorName,buildingName);
	}
}
