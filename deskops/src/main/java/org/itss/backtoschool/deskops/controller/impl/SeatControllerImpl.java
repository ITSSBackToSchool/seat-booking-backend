package org.itss.backtoschool.deskops.controller.impl;

import lombok.RequiredArgsConstructor;

import org.itss.backtoschool.deskops.config.security.CurrentUser;
import org.itss.backtoschool.deskops.controller.SeatController;
import org.itss.backtoschool.deskops.dto.CreateSeatRequest;
import org.itss.backtoschool.deskops.dto.SeatDTO;
import org.itss.backtoschool.deskops.entities.Permission;
import org.itss.backtoschool.deskops.entities.User;
import org.itss.backtoschool.deskops.service.SeatService;
import org.itss.backtoschool.deskops.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatControllerImpl implements SeatController {

    private final SeatService seatService;
    private final UserService userService;

    @Override
    public ResponseEntity<List<SeatDTO>> getAllSeats() {
        return ResponseEntity.ok(seatService.getAllSeats());
    }

    @Override
    public ResponseEntity<List<SeatDTO>> getAvailableSeats(LocalDate date, Long buildingId) {
        return ResponseEntity.ok(seatService.getAvailableSeats(date, buildingId));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSeat(@CurrentUser Jwt jwt, @Valid @org.springframework.web.bind.annotation.RequestBody CreateSeatRequest request) {
        User user = userService.getOrCreateUser(jwt);
        if (!user.hasPermission(Permission.CREATE_SEAT)) {
            return ResponseEntity.status(403).body("Forbidden: user lacks CREATE_SEAT permission");
        }

        var created = seatService.createSeat(request.getSeatNumber(), request.getRoomId());
        return ResponseEntity.status(201).body(created);
    }

}
