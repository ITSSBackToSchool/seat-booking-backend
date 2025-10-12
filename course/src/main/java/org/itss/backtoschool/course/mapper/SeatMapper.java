package org.itss.backtoschool.course.mapper;

import org.itss.backtoschool.course.dto.SeatDTO;
import org.itss.backtoschool.course.entities.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeatMapper {

	@Mapping(source = "floor.name", target = "floorName")
	@Mapping(source = "floor.building.name", target = "buildingName")
	SeatDTO toDTO(Seat seat);

	List<SeatDTO> toDTOList(List<Seat> seats);
}

