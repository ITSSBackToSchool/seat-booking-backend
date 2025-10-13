package org.itss.backtoschool.course.controller;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.dto.response.TrafficResponse;
import org.itss.backtoschool.course.service.TrafficService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TrafficController {

    private final TrafficService trafficService;

    @GetMapping("/api/traffic")
    public ResponseEntity<?> getTrafficInfo(
            @RequestParam String origin,
            @RequestParam String destination
    ) {
        try {
            TrafficResponse response = trafficService.getTrafficInfo(origin, destination);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Eroare: " + e.getMessage());
        }
    }
}



//http://localhost:8080/api/traffic?origin=44.4268,26.1025&destination=44.4396,26.0963