package org.itss.backtoschool.course.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.itss.backtoschool.course.controller.TrafficController;
import org.itss.backtoschool.course.dto.request.TrafficRouteRequest;
import org.itss.backtoschool.course.dto.response.TrafficRouteResponse;
import org.itss.backtoschool.course.service.TrafficService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/traffic")
@RequiredArgsConstructor
public class TrafficControllerImpl implements TrafficController {

    private final TrafficService trafficService;

    @Override
    public ResponseEntity<TrafficRouteResponse> getRouteToHeadquarters(TrafficRouteRequest request) {
        log.info("Received request for route to headquarters from: {}", request.getOriginAddress());

        TrafficRouteResponse response = trafficService.getRoutesToHeadquarters(request);

        log.info("Returning {} routes and {} traffic incidents",
                response.getRoutes().size(),
                response.getTrafficIncidents().size());

        return ResponseEntity.ok(response);
    }
}
