package org.itss.backtoschool.deskops.mapper;

import org.itss.backtoschool.deskops.dto.ReservationDTO;
import org.itss.backtoschool.deskops.entities.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(source = "reservationDate", target = "reservationDate")
    @Mapping(source = "startTime", target = "startTime")
    @Mapping(source = "endTime", target = "endTime")
    @Mapping(target = "isAllDayBooking", expression = "java(reservation.getStartTime() == null)")
    @Mapping(source = "bookEntireRoom", target = "bookEntireRoom")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "seat.id", target = "seatId")
    @Mapping(source = "seat.seatNumber", target = "seatNumber")
    @Mapping(source = "seat.room.id", target = "roomId")
    @Mapping(source = "seat.room.name", target = "roomName")
    @Mapping(source = "seat.room.roomType", target = "roomType")
    @Mapping(source = "seat.room.floor.name", target = "floorName")
    @Mapping(source = "seat.room.floor.building.name", target = "buildingName")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.name", target = "userName")
    @Mapping(source = "user.email", target = "userEmail")
    @Mapping(target = "weather", ignore = true)
    @Mapping(target = "traffic", ignore = true)
    ReservationDTO toDTO(Reservation reservation);

}
