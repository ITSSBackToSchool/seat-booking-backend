package org.itss.backtoschool.course.controller.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.controller.ReservationController;
import org.itss.backtoschool.course.dto.ReservationDTO;
import org.itss.backtoschool.course.dto.request.CreateReservationRequest;
import org.itss.backtoschool.course.dto.request.CreateReservationRoomRequest;
import org.itss.backtoschool.course.dto.response.CreateReservationResponse;
import org.itss.backtoschool.course.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<ReservationDTO> findAll(){
        return reservationService.findAll();
    }

    @Override
    public List<ReservationDTO> findReservationsByUserId(Long userId) {
        return reservationService.findReservationsByUserId(userId);
    }

    @Override
    public String cancelReservation(Long reservationId) {
        return reservationService.cancelResevation(reservationId);
    }

    @Override
    public ResponseEntity<ReservationDTO> createRoomReservation(CreateReservationRoomRequest request) {
        ReservationDTO response = reservationService.createRoomReservation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}