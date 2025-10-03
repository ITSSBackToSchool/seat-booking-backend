package org.itss.backtoschool.course.service;

import org.itss.backtoschool.course.dto.ReservationSeatDTO;
import org.itss.backtoschool.course.dto.ReservationRoomDTO;
import org.itss.backtoschool.course.dto.request.CreateReservationSeatRequest;
import org.itss.backtoschool.course.dto.request.CreateReservationRoomRequest;
import org.itss.backtoschool.course.dto.response.CreateReservationSeatResponse;
import org.itss.backtoschool.course.dto.response.CreateReservationRoomResponse;

import java.util.List;

public interface ReservationService {

    CreateReservationSeatResponse createReservationsForSeats(CreateReservationSeatRequest request);

    CreateReservationRoomResponse createReservationsForRooms(CreateReservationRoomRequest request);

    List<ReservationSeatDTO> getAllSeatReservations();

    List<ReservationRoomDTO> getAllRoomReservations();
}
