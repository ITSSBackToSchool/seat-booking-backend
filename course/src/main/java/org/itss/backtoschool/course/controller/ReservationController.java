package org.itss.backtoschool.course.controller;

import org.apache.coyote.Response;
import org.itss.backtoschool.course.dto.ReservationDTO;
import org.itss.backtoschool.course.dto.request.CreateReservationRequest;
import org.itss.backtoschool.course.dto.response.CreateReservationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ReservationController {

    @PostMapping
    ResponseEntity<CreateReservationResponse> createReservations(@RequestBody CreateReservationRequest request);

    @GetMapping
    List<ReservationDTO> findAll();
}
