package org.itss.backtoschool.course.service;

import org.itss.backtoschool.course.dto.SeatAvailabilityDTO;
import org.itss.backtoschool.course.dto.SeatDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface SeatService {
    List<SeatDTO> getAllSeats();
    
    List<SeatDTO> getSeatsByRoomId(Long roomId);
    
    List<SeatAvailabilityDTO> getAvailableSeats(
            Long buildingId,
            Long floorId,
            Long roomId,
            LocalDate date,
            LocalTime startTime,
            LocalTime endTime
    );
}
