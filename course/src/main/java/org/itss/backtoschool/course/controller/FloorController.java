package org.itss.backtoschool.course.controller;

import org.itss.backtoschool.course.dto.FloorDTO;
import org.itss.backtoschool.course.dto.RoomDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface FloorController {
    
    @GetMapping
    ResponseEntity<List<FloorDTO>> getAllFloors();
    
    @GetMapping("/{id}")
    ResponseEntity<FloorDTO> getFloorById(@PathVariable Long id);
    
    @GetMapping("/{floorId}/rooms")
    ResponseEntity<List<RoomDTO>> getRoomsByFloorId(@PathVariable Long floorId);
    
    @GetMapping("/building/{buildingId}")
    ResponseEntity<List<FloorDTO>> getFloorsByBuildingId(@PathVariable Long buildingId);
}

