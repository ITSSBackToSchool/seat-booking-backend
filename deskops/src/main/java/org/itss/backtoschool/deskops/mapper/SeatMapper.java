package org.itss.backtoschool.deskops.mapper;

import org.itss.backtoschool.deskops.dto.SeatDTO;
import org.itss.backtoschool.deskops.entities.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeatMapper {

    @Mapping(source = "room.name", target = "roomName")
    @Mapping(source = "room.floor.name", target = "floorName")
    @Mapping(source = "room.floor.building.name", target = "buildingName")
    SeatDTO toDTO(Seat seat);

    List<SeatDTO> toDTOList(List<Seat> seats);
}
