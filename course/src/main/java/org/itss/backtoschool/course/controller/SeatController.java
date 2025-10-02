package org.itss.backtoschool.course.controller;

import org.itss.backtoschool.course.dto.SeatDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SeatController {

    @GetMapping
    ResponseEntity<List<SeatDTO>> getAllSeats();

    @GetMapping("/free")
    List<SeatDTO> getAvailableSeats(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateStart,
                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateEnd);
}
