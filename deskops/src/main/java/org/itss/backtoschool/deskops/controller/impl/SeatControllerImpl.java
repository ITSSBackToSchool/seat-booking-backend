package org.itss.backtoschool.deskops.controller.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.deskops.controller.SeatController;
import org.itss.backtoschool.deskops.dto.SeatDTO;
import org.itss.backtoschool.deskops.service.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatControllerImpl implements SeatController {

    private final SeatService seatService;

    @Override
    public ResponseEntity<List<SeatDTO>> getAllSeats() {
        return ResponseEntity.ok(seatService.getAllSeats());
    }

    @Override
    public ResponseEntity<List<SeatDTO>> getAvailableSeats(LocalDate date, Long buildingId) {
        return ResponseEntity.ok(seatService.getAvailableSeats(date, buildingId));
    }
}
