package org.itss.backtoschool.deskops.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.itss.backtoschool.deskops.dto.CreateRoomRequest;
import org.itss.backtoschool.deskops.dto.RoomDTO;
import org.itss.backtoschool.deskops.dto.response.RoomAvailabilityResponse;
import org.itss.backtoschool.deskops.entities.User;
import org.itss.backtoschool.deskops.service.RoomService;
import org.itss.backtoschool.deskops.service.UserService;
import org.itss.backtoschool.deskops.config.security.CurrentUser;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;

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

    /**
     * Get the complete availability timeline for a room on a specific date.
     * Shows all booked and available time slots.
     * Example: GET /api/rooms/4/availability?date=2025-10-15
     *
     * @param roomId the room ID
     * @param date the date to check (format: yyyy-MM-dd)
     * @return complete timeline with booked and available slots
     */
    @GetMapping("/{roomId}/availability")
    @PreAuthorize("hasAuthority('VIEW_SEATS')")
    public ResponseEntity<RoomAvailabilityResponse> getRoomAvailability(
            @PathVariable Long roomId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        log.debug("Getting availability for room {} on date {}", roomId, date);
        RoomAvailabilityResponse availability = roomService.getRoomAvailability(roomId, date);
        return ResponseEntity.ok(availability);
    }

    /**
     * Delete a room by ID.
     * This will also delete all associated seats and reservations.
     * Example: DELETE /api/rooms/4
     *
     * @param jwt the authenticated user's JWT token
     * @param roomId the room ID to delete
     * @return 204 No Content on success
     */
    @DeleteMapping("/{roomId}")
    @PreAuthorize("hasAuthority('MANAGE_ROOMS')")
    public ResponseEntity<Void> deleteRoom(@CurrentUser Jwt jwt, @PathVariable Long roomId) {
        User user = userService.getOrCreateUser(jwt);
        log.info("User '{}' (id: {}) deleting room with ID: {}", user.getName(), user.getId(), roomId);

        roomService.deleteRoom(roomId);
        return ResponseEntity.noContent().build();
    }
}
