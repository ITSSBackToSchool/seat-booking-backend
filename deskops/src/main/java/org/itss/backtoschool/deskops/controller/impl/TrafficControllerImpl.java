package org.itss.backtoschool.deskops.controller.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.deskops.controller.TrafficController;
import org.itss.backtoschool.deskops.dto.request.GetTrafficRequest;
import org.itss.backtoschool.deskops.dto.response.GetTrafficResponse;
import org.itss.backtoschool.deskops.service.TrafficService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/traffic")
@RequiredArgsConstructor
public class TrafficControllerImpl implements TrafficController {

    private final TrafficService trafficService;

    @Override
    public ResponseEntity<GetTrafficResponse> getTrafficRecommendation(String street, int streetNumber, String city) {

        return ResponseEntity.ok(trafficService.getTrafficRecommendation(street, streetNumber, city));
    }
}
