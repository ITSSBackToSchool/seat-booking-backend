package org.itss.backtoschool.course.controller.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.controller.ReservationController;
import org.itss.backtoschool.course.dto.request.CreateReservationRoomRequest;
import org.itss.backtoschool.course.dto.response.CreateReservationRoomResponse;
import org.itss.backtoschool.course.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationControllerImpl implements ReservationController {

    private final ReservationService reservationService;

    @Override
    public ResponseEntity<CreateReservationRoomResponse> createReservations(CreateReservationRoomRequest request) {
        CreateReservationRoomResponse response = reservationService.createReservations(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}