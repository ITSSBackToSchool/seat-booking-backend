package org.itss.backtoschool.course.controller;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.service.PollenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PollenController {

    private final PollenService pollenService;

    @GetMapping("/api/pollen")
    public ResponseEntity<?> getPollen(
            @RequestParam double lat,
            @RequestParam double lon,
            @RequestParam(defaultValue = "1") int days
    ) {
        try {
            String json = pollenService.getPollenForecast(lat, lon, days);
            return ResponseEntity.ok(json);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}


