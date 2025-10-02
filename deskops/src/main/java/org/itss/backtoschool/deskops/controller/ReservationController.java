package org.itss.backtoschool.deskops.controller;

import org.itss.backtoschool.deskops.dto.request.CreateReservationRequest;
import org.itss.backtoschool.deskops.dto.request.UpdateReservationRequest;
import org.itss.backtoschool.deskops.dto.response.CreateReservationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ReservationController {

    @PostMapping
    ResponseEntity<CreateReservationResponse> createReservations(@RequestBody CreateReservationRequest request);

    @GetMapping
    ResponseEntity<CreateReservationResponse> getAllReservations();

    @GetMapping("/{reservationId}")
    ResponseEntity<CreateReservationResponse> getReservation(@PathVariable Long reservationId);

    @DeleteMapping("/{reservationId}")
    ResponseEntity<Void> deleteReservation(@PathVariable Long reservationId);

    @PatchMapping("/{reservationId}")
    ResponseEntity<CreateReservationResponse> updateReservation(
            @PathVariable Long reservationId,
            @RequestBody UpdateReservationRequest request);
}
