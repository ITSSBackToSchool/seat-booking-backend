package org.itss.backtoschool.course.controller.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.controller.FloorController;
import org.itss.backtoschool.course.dto.FloorDTO;
import org.itss.backtoschool.course.dto.RoomDTO;
import org.itss.backtoschool.course.service.FloorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/floors")
@RequiredArgsConstructor
public class FloorControllerImpl implements FloorController {
    
    private final FloorService floorService;
    
    @Override
    public ResponseEntity<List<FloorDTO>> getAllFloors() {
        return ResponseEntity.ok(floorService.getAllFloors());
    }
    
    @Override
    public ResponseEntity<FloorDTO> getFloorById(@PathVariable Long id) {
        return ResponseEntity.ok(floorService.getFloorById(id));
    }
    
    @Override
    public ResponseEntity<List<RoomDTO>> getRoomsByFloorId(@PathVariable Long floorId) {
        return ResponseEntity.ok(floorService.getRoomsByFloorId(floorId));
    }
    
    @Override
    public ResponseEntity<List<FloorDTO>> getFloorsByBuildingId(@PathVariable Long buildingId) {
        return ResponseEntity.ok(floorService.getFloorsByBuildingId(buildingId));
    }
}

