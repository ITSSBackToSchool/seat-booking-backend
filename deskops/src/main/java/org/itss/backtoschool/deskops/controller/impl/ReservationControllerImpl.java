package org.itss.backtoschool.deskops.controller.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.deskops.controller.ReservationController;
import org.itss.backtoschool.deskops.dto.request.CreateReservationRequest;
import org.itss.backtoschool.deskops.dto.request.UpdateReservationRequest;
import org.itss.backtoschool.deskops.dto.response.CreateReservationResponse;
import org.itss.backtoschool.deskops.service.ReservationService;
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
    public ResponseEntity<CreateReservationResponse> createReservations(CreateReservationRequest request) {
        CreateReservationResponse response = reservationService.createReservations(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<CreateReservationResponse> getAllReservations() {
        CreateReservationResponse response = reservationService.getAllReservations();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<CreateReservationResponse> getReservation(Long reservationId) {
        CreateReservationResponse response = reservationService.getReservation(reservationId);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deleteReservation(Long reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CreateReservationResponse> updateReservation(Long reservationId, UpdateReservationRequest request) {
        CreateReservationResponse response = reservationService.updateReservation(reservationId, request);
        return ResponseEntity.ok(response);
    }
}