package org.itss.backtoschool.course.controller;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.dto.UserDTO;
import org.itss.backtoschool.course.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        try {
            UserDTO updatedUser = userService.updateUser(userId, userDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to update user: " + e.getMessage());
        }
    }

}
