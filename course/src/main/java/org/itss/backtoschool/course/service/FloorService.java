package org.itss.backtoschool.course.service;

import org.itss.backtoschool.course.dto.FloorDTO;
import org.itss.backtoschool.course.dto.RoomDTO;

import java.util.List;

public interface FloorService {
    List<FloorDTO> getAllFloors();
    
    FloorDTO getFloorById(Long id);
    
    List<RoomDTO> getRoomsByFloorId(Long floorId);
    
    List<FloorDTO> getFloorsByBuildingId(Long buildingId);
}

