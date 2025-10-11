package org.itss.backtoschool.course.mapper;

import org.itss.backtoschool.course.dto.BuildingDTO;
import org.itss.backtoschool.course.entities.Building;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BuildingMapper {
    
    BuildingDTO toDTO(Building building);
    
    List<BuildingDTO> toDTOList(List<Building> buildings);
}

