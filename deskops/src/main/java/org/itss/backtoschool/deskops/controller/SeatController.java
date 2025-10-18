package org.itss.backtoschool.deskops.controller;

import org.itss.backtoschool.deskops.config.security.CurrentUser;
import org.itss.backtoschool.deskops.dto.CreateSeatRequest;
import org.itss.backtoschool.deskops.dto.SeatDTO;
import org.itss.backtoschool.deskops.dto.UpdateSeatRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

public interface SeatController {

    @GetMapping
    ResponseEntity<List<SeatDTO>> getAllSeats();

    @GetMapping("/available")
    ResponseEntity<List<SeatDTO>> getAvailableSeats(
            @RequestParam LocalDate date,
            @RequestParam(required = false) Long buildingId);

    @PostMapping("/add")
    ResponseEntity<?> addSeat(
            @CurrentUser Jwt jwt,
            @Valid @org.springframework.web.bind.annotation.RequestBody CreateSeatRequest request);

    @DeleteMapping("/{seatId}")
    ResponseEntity<Void> deleteSeat(
            @CurrentUser Jwt jwt,
            @PathVariable Long seatId);

    @PutMapping("/{seatId}")
    ResponseEntity<SeatDTO> updateSeat(
            @CurrentUser Jwt jwt,
            @PathVariable Long seatId,
            @Valid @org.springframework.web.bind.annotation.RequestBody UpdateSeatRequest request);

}
