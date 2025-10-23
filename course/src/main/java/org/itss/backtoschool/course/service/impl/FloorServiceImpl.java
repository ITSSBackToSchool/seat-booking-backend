package org.itss.backtoschool.course.service.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.dto.FloorDTO;
import org.itss.backtoschool.course.dto.RoomDTO;
import org.itss.backtoschool.course.entities.Floor;
import org.itss.backtoschool.course.mapper.FloorMapper;
import org.itss.backtoschool.course.mapper.RoomMapper;
import org.itss.backtoschool.course.repository.FloorRepository;
import org.itss.backtoschool.course.repository.RoomRepository;
import org.itss.backtoschool.course.service.FloorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FloorServiceImpl implements FloorService {
    
    private final FloorRepository floorRepository;
    private final RoomRepository roomRepository;
    private final FloorMapper floorMapper;
    private final RoomMapper roomMapper;
    
    @Override
    public List<FloorDTO> getAllFloors() {
        var floors = floorRepository.findAll();
        return floorMapper.toDTOList(floors);
    }
    
    @Override
    public FloorDTO getFloorById(Long id) {
        Floor floor = floorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Floor not found with id: " + id));
        return floorMapper.toDTO(floor);
    }
    
    @Override
    public List<RoomDTO> getRoomsByFloorId(Long floorId) {

        floorRepository.findById(floorId)
                .orElseThrow(() -> new RuntimeException("Floor not found with id: " + floorId));
        
        var rooms = roomRepository.findByFloorId(floorId);
        return roomMapper.toDTOList(rooms);
    }
    
    @Override
    public List<FloorDTO> getFloorsByBuildingId(Long buildingId) {
        var floors = floorRepository.findByBuildingId(buildingId);
        return floorMapper.toDTOList(floors);
    }
}

