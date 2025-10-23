package org.itss.backtoschool.course.controller;

import org.itss.backtoschool.course.dto.UserReservationDTO;
import org.itss.backtoschool.course.dto.request.CreateReservationSeatRequest;
import org.itss.backtoschool.course.dto.request.CreateReservationRoomRequest;
import org.itss.backtoschool.course.dto.request.UpdateReservationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ReservationController {

    @PostMapping("/seat")
    ResponseEntity<?> createSeatReservations(@RequestBody CreateReservationSeatRequest request);

    @PostMapping("/room")
    ResponseEntity<?> createRoomReservations(@RequestBody CreateReservationRoomRequest request);
    
    @GetMapping("/user/{userId}")
    ResponseEntity<List<UserReservationDTO>> getUserReservations(@PathVariable Long userId);
    
    @PutMapping("/{reservationId}")
    ResponseEntity<UserReservationDTO> updateReservation(
        @PathVariable Long reservationId, 
        @RequestBody UpdateReservationRequest request
    );
    
    @DeleteMapping("/{reservationId}")
    ResponseEntity<Void> cancelReservation(@PathVariable Long reservationId);
}
