package org.itss.backtoschool.course.mapper;

import org.itss.backtoschool.course.dto.RoomDTO;
import org.itss.backtoschool.course.entities.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {

	@Mapping(source = "room.floor.name",target = "floorName")
	@Mapping(source = "room.floor.building.name",target = "buildingName")
	@Mapping(source = "room.name",target = "name")
	@Mapping(source = "room.id",target = "roomId")
	@Mapping(source = "room.seatCount",target = "seatCount")
	RoomDTO toDTO(Room room);

	List<RoomDTO> toDTOList(List<Room> rooms);
}
