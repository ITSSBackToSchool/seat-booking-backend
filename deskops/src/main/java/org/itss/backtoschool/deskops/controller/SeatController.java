package org.itss.backtoschool.deskops.controller;

import org.itss.backtoschool.deskops.dto.SeatDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface SeatController {

    @GetMapping
    ResponseEntity<List<SeatDTO>> getAllSeats();
}
