package org.itss.backtoschool.course.controller.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.controller.SeatController;
import org.itss.backtoschool.course.dto.SeatDTO;
import org.itss.backtoschool.course.entities.ReservationStatus;
import org.itss.backtoschool.course.entities.Seat;
import org.itss.backtoschool.course.service.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public List<SeatDTO> findAvailableSeatsByRoomAndFloor(Long floorId, String buildingName, LocalDateTime dateStart, LocalDateTime dateEnd) {
        return seatService.findAvailableSeatsByRoomAndFloor(floorId,buildingName,dateStart,dateEnd);
    }

}
