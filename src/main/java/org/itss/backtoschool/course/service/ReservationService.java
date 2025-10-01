package org.itss.backtoschool.course.service;

import org.itss.backtoschool.course.dto.request.CreateReservationSeatRequest;
import org.itss.backtoschool.course.dto.request.CreateReservationRoomRequest;
import org.itss.backtoschool.course.dto.response.CreateReservationSeatResponse;
import org.itss.backtoschool.course.dto.response.CreateReservationRoomResponse;

public interface ReservationService {


    CreateReservationSeatResponse createReservationsForSeats(CreateReservationSeatRequest request);


    CreateReservationRoomResponse createReservationsForRooms(CreateReservationRoomRequest request);
}
