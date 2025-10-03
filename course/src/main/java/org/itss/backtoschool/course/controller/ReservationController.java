package org.itss.backtoschool.course.controller;

import org.itss.backtoschool.course.dto.request.CreateReservationSeatRequest;
import org.itss.backtoschool.course.dto.request.CreateReservationRoomRequest;
import org.springframework.http.ResponseEntity;

public interface ReservationController {

    ResponseEntity<?> createSeatReservations(CreateReservationSeatRequest request);

    ResponseEntity<?> createRoomReservations(CreateReservationRoomRequest request);
}
