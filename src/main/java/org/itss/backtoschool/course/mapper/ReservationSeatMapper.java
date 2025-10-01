package org.itss.backtoschool.course.mapper;

import org.itss.backtoschool.course.dto.ReservationSeatDTO;
import org.itss.backtoschool.course.entities.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationSeatMapper {

    @Mapping(source = "status", target = "status")
    @Mapping(source = "seat.id", target = "seatId")
    @Mapping(source = "seat.seatNumber", target = "seatNumber")
    @Mapping(source = "seat.room.name", target = "roomName")
    @Mapping(source = "seat.room.floor.name", target = "floorName")
    @Mapping(source = "seat.room.floor.building.name", target = "buildingName")
    @Mapping(source = "users.id", target = "userId")
    @Mapping(source = "users.userName", target = "userName")
    @Mapping(source = "users.email", target = "userEmail")
    ReservationSeatDTO toDTO(Reservation reservation);

}
