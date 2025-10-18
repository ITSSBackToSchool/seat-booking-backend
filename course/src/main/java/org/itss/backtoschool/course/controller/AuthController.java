package org.itss.backtoschool.course.controller;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.dto.UserDTO;
import org.itss.backtoschool.course.security.JwtService;
import org.itss.backtoschool.course.security.JwtUtil;
import org.itss.backtoschool.course.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO request) {
        Optional<UserDTO> userOpt = userService.findByEmail(request.getEmail());
        if (userOpt.isEmpty() || !passwordEncoder.matches(request.getPassword(), userOpt.get().getPassword())) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }

        String token = jwtService.generateToken(userOpt.get().getUserName());
        return ResponseEntity.ok(Map.of(
                "id", userOpt.get().getId(),
                "token", token,
                "userName", userOpt.get().getUserName(),
                "role", userOpt.get().getRole() != null ? userOpt.get().getRole() : "EMPLOYEE"
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        UserDTO savedUser = userService.register(userDTO);
        String token = jwtService.generateToken(savedUser.getUserName());
        return ResponseEntity.ok(Map.of(
                "id", savedUser.getId(),
                "token", token,
                "userName", savedUser.getUserName(),
                "role", savedUser.getRole() != null ? savedUser.getRole() : "EMPLOYEE"
        ));
    }
}
