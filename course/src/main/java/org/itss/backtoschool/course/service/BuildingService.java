package org.itss.backtoschool.course.service;

import org.itss.backtoschool.course.dto.BuildingDTO;
import org.itss.backtoschool.course.dto.FloorDTO;

import java.util.List;

public interface BuildingService {
    List<BuildingDTO> getAllBuildings();
    
    BuildingDTO getBuildingById(Long id);
    
    List<FloorDTO> getFloorsByBuildingId(Long buildingId);
}

