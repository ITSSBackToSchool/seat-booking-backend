package org.itss.backtoschool.course.service;

import org.itss.backtoschool.course.dto.RoomDTO;
import java.util.List;

public interface RoomService {
    List<RoomDTO> getAllRooms();
    List<RoomDTO> getRoomsByFloorId(Long floorId);
    List<RoomDTO> getRoomsByBuildingId(Long buildingId);
}
