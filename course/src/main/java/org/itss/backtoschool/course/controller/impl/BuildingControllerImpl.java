package org.itss.backtoschool.course.controller.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.controller.BuildingController;
import org.itss.backtoschool.course.dto.BuildingDTO;
import org.itss.backtoschool.course.dto.FloorDTO;
import org.itss.backtoschool.course.service.BuildingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/buildings")
@RequiredArgsConstructor
public class BuildingControllerImpl implements BuildingController {
    
    private final BuildingService buildingService;
    
    @Override
    public ResponseEntity<List<BuildingDTO>> getAllBuildings() {
        return ResponseEntity.ok(buildingService.getAllBuildings());
    }
    
    @Override
    public ResponseEntity<BuildingDTO> getBuildingById(@PathVariable Long id) {
        return ResponseEntity.ok(buildingService.getBuildingById(id));
    }
    
    @Override
    public ResponseEntity<List<FloorDTO>> getFloorsByBuildingId(@PathVariable Long buildingId) {
        return ResponseEntity.ok(buildingService.getFloorsByBuildingId(buildingId));
    }
}

