package org.itss.backtoschool.deskops.controller;

import org.itss.backtoschool.deskops.config.security.CurrentUser;
import org.itss.backtoschool.deskops.dto.request.CreateReservationRequest;
import org.itss.backtoschool.deskops.dto.request.UpdateReservationRequest;
import org.itss.backtoschool.deskops.dto.response.CreateReservationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

public interface ReservationController {

    @PostMapping
    ResponseEntity<CreateReservationResponse> createReservations(
            @RequestBody CreateReservationRequest request,
            @CurrentUser Jwt jwt);

    @GetMapping
    ResponseEntity<CreateReservationResponse> getAllReservations(@CurrentUser Jwt jwt);

    @GetMapping("/{reservationId}")
    ResponseEntity<CreateReservationResponse> getReservation(
            @PathVariable Long reservationId,
            @CurrentUser Jwt jwt);

    @DeleteMapping("/{reservationId}")
    ResponseEntity<Void> deleteReservation(
            @PathVariable Long reservationId,
            @CurrentUser Jwt jwt);

    @PatchMapping("/{reservationId}")
    ResponseEntity<CreateReservationResponse> updateReservation(
            @PathVariable Long reservationId,
            @RequestBody UpdateReservationRequest request,
            @CurrentUser Jwt jwt);
}
