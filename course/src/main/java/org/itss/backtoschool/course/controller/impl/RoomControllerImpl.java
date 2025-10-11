package org.itss.backtoschool.course.controller.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.controller.RoomController;
import org.itss.backtoschool.course.dto.RoomDTO;
import org.itss.backtoschool.course.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomControllerImpl implements RoomController {

    private final RoomService roomService;

    @Override
    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @Override
    @GetMapping("/floor/{floorId}")
    public ResponseEntity<List<RoomDTO>> getRoomsByFloorId(@PathVariable Long floorId) {
        return ResponseEntity.ok(roomService.getRoomsByFloorId(floorId));
    }

    @Override
    @GetMapping("/building/{buildingId}")
    public ResponseEntity<List<RoomDTO>> getRoomsByBuildingId(@PathVariable Long buildingId) {
        return ResponseEntity.ok(roomService.getRoomsByBuildingId(buildingId));
    }
    
    @Override
    public ResponseEntity<List<RoomDTO>> getRoomsByFloorId(Long floorId) {
        List<Room> rooms = roomRepository.findByFloorId(floorId);
        List<RoomDTO> roomDTOs = roomMapper.toDTOList(rooms);
        return ResponseEntity.ok(roomDTOs);
    }
    
    @Override
    public ResponseEntity<List<RoomDTO>> getRoomsByBuildingId(Long buildingId) {
        List<Room> rooms = roomRepository.findByFloor_BuildingId(buildingId);
        List<RoomDTO> roomDTOs = roomMapper.toDTOList(rooms);
        return ResponseEntity.ok(roomDTOs);
    }
}
