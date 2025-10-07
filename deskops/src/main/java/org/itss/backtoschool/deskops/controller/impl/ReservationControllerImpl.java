package org.itss.backtoschool.deskops.controller.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.deskops.controller.ReservationController;
import org.itss.backtoschool.deskops.dto.request.CreateReservationRequest;
import org.itss.backtoschool.deskops.dto.request.UpdateReservationRequest;
import org.itss.backtoschool.deskops.dto.response.CreateReservationResponse;
import org.itss.backtoschool.deskops.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationControllerImpl implements ReservationController {

    private final ReservationService reservationService;

    @Override
    @PreAuthorize("hasAuthority('RESERVE_SEAT')")
    public ResponseEntity<CreateReservationResponse> createReservations(CreateReservationRequest request, Jwt jwt) {
        CreateReservationResponse response = reservationService.createReservations(request, jwt);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    @PreAuthorize("hasAuthority('RESERVE_SEAT')")
    public ResponseEntity<CreateReservationResponse> getAllReservations(Jwt jwt) {
        CreateReservationResponse response = reservationService.getAllReservations(jwt);
        return ResponseEntity.ok(response);
    }

    @Override
    @PreAuthorize("hasAuthority('RESERVE_SEAT')")
    public ResponseEntity<CreateReservationResponse> getReservation(Long reservationId, Jwt jwt) {
        CreateReservationResponse response = reservationService.getReservation(reservationId, jwt);
        return ResponseEntity.ok(response);
    }

    @Override
    @PreAuthorize("hasAuthority('CANCEL_RESERVATION')")
    public ResponseEntity<Void> deleteReservation(Long reservationId, Jwt jwt) {
        reservationService.deleteReservation(reservationId, jwt);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PreAuthorize("hasAuthority('RESERVE_SEAT') and hasAuthority('CANCEL_RESERVATION')")
    public ResponseEntity<CreateReservationResponse> updateReservation(Long reservationId, UpdateReservationRequest request, Jwt jwt) {
        CreateReservationResponse response = reservationService.updateReservation(reservationId, request, jwt);
        return ResponseEntity.ok(response);
    }
}