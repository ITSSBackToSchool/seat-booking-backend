package org.itss.backtoschool.course.controller;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.dto.response.TrafficResponse;
import org.itss.backtoschool.course.service.TrafficService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TrafficController {

    private final TrafficService trafficService;

    @GetMapping("/api/traffic")
    public ResponseEntity<?> getTrafficInfo(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam(required = false) String departureTime
    ) {
        try {
            LocalDateTime departureDateTime = null;
            if (departureTime != null && !departureTime.isEmpty()) {
                Instant instant = Instant.parse(departureTime);
                departureDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
                System.out.println("Parsed departure time: " + departureDateTime);
            }
            
            TrafficResponse response = trafficService.getTrafficInfo(origin, destination, departureDateTime);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}




