package org.itss.backtoschool.deskops.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.itss.backtoschool.deskops.dto.CreateRoomRequest;
import org.itss.backtoschool.deskops.dto.RoomDTO;
import org.itss.backtoschool.deskops.entities.User;
import org.itss.backtoschool.deskops.service.RoomService;
import org.itss.backtoschool.deskops.service.UserService;
import org.itss.backtoschool.deskops.config.security.CurrentUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomControllerImpl {

    private final RoomService roomService;
    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasAuthority('MANAGE_ROOMS')")
    public ResponseEntity<?> createRoom(@CurrentUser Jwt jwt, @Valid @RequestBody CreateRoomRequest request) {
        // Ensure user exists in database for audit trail
        User user = userService.getOrCreateUser(jwt);

        log.info("User '{}' (id: {}) creating room '{}'", user.getName(), user.getId(), request.getName());
        RoomDTO created = roomService.createRoom(request);
        return ResponseEntity.status(201).body(created);
    }
}
