package org.itss.backtoschool.course.controller.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.controller.ReservationController;
import org.itss.backtoschool.course.dto.request.CreateReservationRoomRequest;
import org.itss.backtoschool.course.dto.request.CreateReservationSeatRequest;
import org.itss.backtoschool.course.dto.response.CreateReservationRoomResponse;
import org.itss.backtoschool.course.dto.response.CreateReservationSeatResponse;
import org.itss.backtoschool.course.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationControllerImpl implements ReservationController {

    private final ReservationService reservationService;

    @Override
    @PostMapping("/rooms")
    public ResponseEntity<CreateReservationRoomResponse> createRoomReservations(
            @RequestBody CreateReservationRoomRequest request) {
        CreateReservationRoomResponse response = reservationService.createReservationsForRooms(request);
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping("/seats")
    public ResponseEntity<CreateReservationSeatResponse> createSeatReservations(
            @RequestBody CreateReservationSeatRequest request) {
        CreateReservationSeatResponse response = reservationService.createReservationsForSeats(request);
        return ResponseEntity.ok(response);
    }
}
