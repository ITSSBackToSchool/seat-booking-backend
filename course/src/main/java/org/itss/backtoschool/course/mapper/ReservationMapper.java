package org.itss.backtoschool.course.mapper;

import org.itss.backtoschool.course.dto.ReservationDTO;
import org.itss.backtoschool.course.entities.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(source = "status", target = "status")
    @Mapping(source = "seat.id", target = "seatId")
    @Mapping(source = "seat.seatNumber", target = "seatNumber")
    @Mapping(source = "seat.floor.name", target = "floorName")
    @Mapping(source = "seat.floor.building.name", target = "buildingName")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.name", target = "userName")
    @Mapping(source = "user.email", target = "userEmail")
    ReservationDTO toDTO(Reservation reservation);
}

