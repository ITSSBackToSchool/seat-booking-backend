package org.itss.backtoschool.course.mapper;

import org.itss.backtoschool.course.dto.ReservationRoomDTO;
import org.itss.backtoschool.course.entities.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationRoomMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "reservationDate", target = "reservationDate")
    @Mapping(source = "status", target = "status")

    @Mapping(source = "users.id", target = "userId")
    @Mapping(source = "users.userName", target = "userName")
    @Mapping(source = "users.email", target = "userEmail")

    @Mapping(source = "room.name", target = "roomName")
    @Mapping(source = "room.floor.name", target = "floorName")
    @Mapping(source = "room.floor.building.name", target = "buildingName")

    @Mapping(source = "startTime", target = "startTime")
    @Mapping(source = "endTime", target = "endTime")
    ReservationRoomDTO toDTO(Reservation reservation);

    List<ReservationRoomDTO> toDTOList(List<Reservation> reservations);
}
