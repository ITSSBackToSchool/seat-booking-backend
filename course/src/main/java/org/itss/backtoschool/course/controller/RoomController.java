package org.itss.backtoschool.course.controller;

import org.itss.backtoschool.course.dto.RoomDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface RoomController {
    ResponseEntity<List<RoomDTO>> getAllRooms();
    ResponseEntity<List<RoomDTO>> getRoomsByFloorId(Long floorId);
    ResponseEntity<List<RoomDTO>> getRoomsByBuildingId(Long buildingId);
    

    ResponseEntity<List<RoomDTO>> getRoomsByFloorIdWithAvailability(
            @PathVariable Long floorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime
    );
    
    ResponseEntity<List<RoomDTO>> getRoomsByBuildingIdWithAvailability(
            @PathVariable Long buildingId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime
    );
}
