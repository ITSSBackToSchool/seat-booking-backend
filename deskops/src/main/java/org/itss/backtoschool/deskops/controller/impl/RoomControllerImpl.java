package org.itss.backtoschool.deskops.controller.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.deskops.dto.CreateRoomRequest;
import org.itss.backtoschool.deskops.dto.RoomDTO;
import org.itss.backtoschool.deskops.entities.Permission;
import org.itss.backtoschool.deskops.entities.User;
import org.itss.backtoschool.deskops.service.RoomService;
import org.itss.backtoschool.deskops.service.UserService;
import org.itss.backtoschool.deskops.config.security.CurrentUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomControllerImpl {

    private final RoomService roomService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createRoom(@CurrentUser Jwt jwt, @Valid @RequestBody CreateRoomRequest request) {
        User user = userService.getOrCreateUser(jwt);
        if (!user.hasPermission(Permission.MANAGE_USERS)) {
            return ResponseEntity.status(403).body("Forbidden: user lacks permission to create rooms");
        }

        RoomDTO created = roomService.createRoom(request);
        return ResponseEntity.status(201).body(created);
    }
}
