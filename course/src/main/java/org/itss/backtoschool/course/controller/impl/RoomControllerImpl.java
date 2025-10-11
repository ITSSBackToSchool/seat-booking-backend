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
}