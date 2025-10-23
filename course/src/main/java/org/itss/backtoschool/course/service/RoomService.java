package org.itss.backtoschool.course.service;

import org.itss.backtoschool.course.dto.RoomDTO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface RoomService {
    List<RoomDTO> getAllRooms();
    List<RoomDTO> getRoomsByFloorId(Long floorId);
    List<RoomDTO> getRoomsByBuildingId(Long buildingId);
    

    List<RoomDTO> getRoomsByFloorIdWithAvailability(Long floorId, LocalDate date, LocalTime startTime, LocalTime endTime);
    List<RoomDTO> getRoomsByBuildingIdWithAvailability(Long buildingId, LocalDate date, LocalTime startTime, LocalTime endTime);
}
