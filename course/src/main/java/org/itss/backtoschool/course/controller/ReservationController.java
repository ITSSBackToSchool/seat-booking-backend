package org.itss.backtoschool.course.controller;

import org.itss.backtoschool.course.dto.request.CreateReservationRequest;
import org.itss.backtoschool.course.dto.response.CreateReservationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ReservationController {

    @PostMapping
    ResponseEntity<CreateReservationResponse> createReservations(@RequestBody CreateReservationRequest request);
}
