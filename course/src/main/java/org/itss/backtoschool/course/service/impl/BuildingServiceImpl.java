package org.itss.backtoschool.course.service.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.dto.BuildingDTO;
import org.itss.backtoschool.course.dto.FloorDTO;
import org.itss.backtoschool.course.entities.Building;
import org.itss.backtoschool.course.mapper.BuildingMapper;
import org.itss.backtoschool.course.mapper.FloorMapper;
import org.itss.backtoschool.course.repository.BuildingRepository;
import org.itss.backtoschool.course.repository.FloorRepository;
import org.itss.backtoschool.course.service.BuildingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {
    
    private final BuildingRepository buildingRepository;
    private final FloorRepository floorRepository;
    private final BuildingMapper buildingMapper;
    private final FloorMapper floorMapper;
    
    @Override
    public List<BuildingDTO> getAllBuildings() {
        var buildings = buildingRepository.findAll();
        return buildingMapper.toDTOList(buildings);
    }
    
    @Override
    public BuildingDTO getBuildingById(Long id) {
        Building building = buildingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Building not found with id: " + id));
        return buildingMapper.toDTO(building);
    }
    
    @Override
    public List<FloorDTO> getFloorsByBuildingId(Long buildingId) {

        buildingRepository.findById(buildingId)
                .orElseThrow(() -> new RuntimeException("Building not found with id: " + buildingId));
        
        var floors = floorRepository.findByBuildingId(buildingId);
        return floorMapper.toDTOList(floors);
    }
}

