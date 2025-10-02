package org.itss.backtoschool.deskops.controller;

import org.itss.backtoschool.deskops.dto.SeatDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

public interface SeatController {

    @GetMapping
    ResponseEntity<List<SeatDTO>> getAllSeats();

    @GetMapping("/available")
    ResponseEntity<List<SeatDTO>> getAvailableSeats(
            @RequestParam LocalDate date,
            @RequestParam(required = false) Long buildingId);
}
