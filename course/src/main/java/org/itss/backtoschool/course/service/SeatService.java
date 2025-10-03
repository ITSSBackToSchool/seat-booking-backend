package org.itss.backtoschool.course.service;

import org.itss.backtoschool.course.dto.SeatDTO;
import org.itss.backtoschool.course.entities.ReservationStatus;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SeatService {
    List<SeatDTO> getAllSeats();
    List<SeatDTO> findAvailableSeatsByRoomAndFloor(Long floorId, String buildingName, LocalDateTime dateStart, LocalDateTime dateEnd);
}
