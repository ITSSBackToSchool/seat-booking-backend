package org.itss.backtoschool.course.controller;

import org.itss.backtoschool.course.dto.SeatAvailabilityDTO;
import org.itss.backtoschool.course.dto.SeatDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface SeatController {

    @GetMapping
    ResponseEntity<List<SeatDTO>> getAllSeats();
    
    @GetMapping("/available")
    ResponseEntity<List<SeatAvailabilityDTO>> getAvailableSeats(
            @RequestParam(required = false) Long buildingId,
            @RequestParam(required = false) Long floorId,
            @RequestParam(required = false) Long roomId,
            @RequestParam LocalDate date,
            @RequestParam LocalTime startTime,
            @RequestParam LocalTime endTime
    );
}
