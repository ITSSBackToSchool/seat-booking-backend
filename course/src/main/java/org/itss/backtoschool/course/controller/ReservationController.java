package org.itss.backtoschool.course.controller;

import org.apache.coyote.Response;
import org.itss.backtoschool.course.dto.ReservationDTO;
import org.itss.backtoschool.course.dto.request.CreateReservationRequest;
import org.itss.backtoschool.course.dto.request.CreateReservationRoomRequest;
import org.itss.backtoschool.course.dto.response.CreateReservationResponse;
import org.itss.backtoschool.course.dto.response.TimeSlot;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationController {

    @PostMapping("/seats")
    ResponseEntity<CreateReservationResponse> createReservations(@RequestBody CreateReservationRequest request);

    @GetMapping
    List<ReservationDTO> findAll();

    @GetMapping("/user/{userId}")
    List<ReservationDTO> findReservationsByUserId(@PathVariable Long userId);

    @PutMapping("/{reservationId}")
    String cancelReservation(@PathVariable Long reservationId);

    @PostMapping("/rooms")
    ResponseEntity<ReservationDTO> createRoomReservation(@RequestBody CreateReservationRoomRequest request);

    @GetMapping("/timeslots")
    List<TimeSlot> findByRoom_IdAndReservationDateStartLessThanAndReservationDateEndGreaterThan(
            @Param("roomId") Long roomId, @Param("dateStart") LocalDateTime dateStart, @Param("dateEnd") LocalDateTime dateEnd
    );

}
