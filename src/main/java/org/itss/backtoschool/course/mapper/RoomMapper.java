package org.itss.backtoschool.course.mapper;

import org.itss.backtoschool.course.dto.RoomDTO;
import org.itss.backtoschool.course.entities.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "floor.id", target = "floorId")
    @Mapping(source = "floor.name", target = "floorName")
    RoomDTO toDTO(Room room);

    List<RoomDTO> toDTOList(List<Room> rooms);
}
