package org.itss.backtoschool.course.controller;

import org.itss.backtoschool.course.dto.RoomDTO;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface RoomController {
    ResponseEntity<List<RoomDTO>> getAllRooms();
    ResponseEntity<List<RoomDTO>> getRoomsByFloorId(Long floorId);
    ResponseEntity<List<RoomDTO>> getRoomsByBuildingId(Long buildingId);
}
