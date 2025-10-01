package org.itss.backtoschool.course.controller;

import org.itss.backtoschool.course.dto.RoomDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public interface RoomController {

    @GetMapping
    ResponseEntity<List<RoomDTO>> getAllRooms();
}
