package org.itss.backtoschool.course.controller.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.controller.RoomController;
import org.itss.backtoschool.course.dto.RoomDTO;
import org.itss.backtoschool.course.entities.Room;
import org.itss.backtoschool.course.mapper.RoomMapper;
import org.itss.backtoschool.course.repository.RoomRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomControllerImpl implements RoomController {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Override
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        List<RoomDTO> roomDTOs = roomMapper.toDTOList(rooms);
        return ResponseEntity.ok(roomDTOs);
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
