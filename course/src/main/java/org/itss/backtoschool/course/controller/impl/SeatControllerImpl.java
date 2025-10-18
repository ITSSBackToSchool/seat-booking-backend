package org.itss.backtoschool.course.controller.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.controller.SeatController;
import org.itss.backtoschool.course.dto.SeatAvailabilityDTO;
import org.itss.backtoschool.course.dto.SeatDTO;
import org.itss.backtoschool.course.service.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
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
    public ResponseEntity<List<SeatAvailabilityDTO>> getAvailableSeats(
            @RequestParam(required = false) Long buildingId,
            @RequestParam(required = false) Long floorId,
            @RequestParam(required = false) Long roomId,
            @RequestParam LocalDate date,
            @RequestParam LocalTime startTime,
            @RequestParam LocalTime endTime
    ) {
        return ResponseEntity.ok(seatService.getAvailableSeats(
                buildingId, floorId, roomId, date, startTime, endTime
        ));
    }
}
