package org.itss.backtoschool.course.mapper;

import org.itss.backtoschool.course.dto.FloorDTO;
import org.itss.backtoschool.course.entities.Floor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FloorMapper {
    
    @Mapping(source = "building.id", target = "buildingId")
    @Mapping(source = "building.name", target = "buildingName")
    FloorDTO toDTO(Floor floor);
    
    List<FloorDTO> toDTOList(List<Floor> floors);
}

