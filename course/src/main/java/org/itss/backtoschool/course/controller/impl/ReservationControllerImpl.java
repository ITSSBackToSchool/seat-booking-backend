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
import java.util.Map;
import java.util.List;
import org.itss.backtoschool.course.dto.ReservationSeatDTO;
import org.itss.backtoschool.course.dto.ReservationRoomDTO;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationControllerImpl implements ReservationController {

    private final ReservationService reservationService;

    @Override
    @PostMapping("/rooms")
    public ResponseEntity<?> createRoomReservations(
            @RequestBody CreateReservationRoomRequest request) {

        try {
            CreateReservationRoomResponse response = reservationService.createReservationsForRooms(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of(
                            "status", 400,
                            "error", e.getMessage()
                    ));
        }
    }

    @Override
    @PostMapping("/seats")
    public ResponseEntity<?> createSeatReservations(
            @RequestBody CreateReservationSeatRequest request) {

        try {
            CreateReservationSeatResponse response = reservationService.createReservationsForSeats(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of(
                            "status", 400,
                            "error", e.getMessage()
                    ));
        }
    }
    @GetMapping("/seats")
    public ResponseEntity<List<ReservationSeatDTO>> getAllSeatReservations() {
        List<ReservationSeatDTO> reservations = reservationService.getAllSeatReservations();
        return ResponseEntity.ok(reservations);
    }

    // GET all room reservations
    @GetMapping("/rooms")
    public ResponseEntity<List<ReservationRoomDTO>> getAllRoomReservations() {
        List<ReservationRoomDTO> reservations = reservationService.getAllRoomReservations();
        return ResponseEntity.ok(reservations);
    }
}
