package org.itss.backtoschool.deskops.controller.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.deskops.controller.TrafficController;
import org.itss.backtoschool.deskops.dto.response.GetTrafficResponse;
import org.itss.backtoschool.deskops.service.TrafficService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/traffic")
@RequiredArgsConstructor
public class TrafficControllerImpl implements TrafficController {

    private final TrafficService trafficService;

    @Override
    @PreAuthorize("hasAuthority('VIEW_TRAFFIC')")
    public ResponseEntity<GetTrafficResponse> getTrafficRecommendation(Long buildingId, Jwt jwt) {
        return ResponseEntity.ok(trafficService.getTrafficRecommendation(buildingId, jwt));
    }
}
