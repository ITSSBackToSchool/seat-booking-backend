package org.itss.backtoschool.course.controller;

import org.itss.backtoschool.course.dto.request.CreateReservationSeatRequest;
import org.itss.backtoschool.course.dto.request.CreateReservationRoomRequest;
import org.itss.backtoschool.course.dto.response.CreateReservationSeatResponse;
import org.itss.backtoschool.course.dto.response.CreateReservationRoomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ReservationController {

    @PostMapping("/seats")
    ResponseEntity<CreateReservationSeatResponse> createSeatReservations(
            @RequestBody CreateReservationSeatRequest request);

    @PostMapping("/rooms")
    ResponseEntity<CreateReservationRoomResponse> createRoomReservations(
            @RequestBody CreateReservationRoomRequest request);
}
