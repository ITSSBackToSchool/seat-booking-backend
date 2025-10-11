package org.itss.backtoschool.course.controller;

import org.itss.backtoschool.course.dto.BuildingDTO;
import org.itss.backtoschool.course.dto.FloorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface BuildingController {
    
    @GetMapping
    ResponseEntity<List<BuildingDTO>> getAllBuildings();
    
    @GetMapping("/{id}")
    ResponseEntity<BuildingDTO> getBuildingById(@PathVariable Long id);
    
    @GetMapping("/{buildingId}/floors")
    ResponseEntity<List<FloorDTO>> getFloorsByBuildingId(@PathVariable Long buildingId);
}

