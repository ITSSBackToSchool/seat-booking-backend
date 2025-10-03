package org.itss.backtoschool.course.controller;

import org.itss.backtoschool.course.dto.SeatDTO;
import org.itss.backtoschool.course.entities.Seat;
import org.springframework.data.repository.query.Param;
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

    @GetMapping("/freeSeats")
    List<SeatDTO> findAvailableSeatsByRoomAndFloor(@Param("floor") Long floorId,
                                                @Param("building") String buildingName,
                                                @Param("dateStart") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateStart,
                                                @Param("dateEnd") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateEnd);
}
