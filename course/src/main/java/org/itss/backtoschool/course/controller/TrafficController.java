package org.itss.backtoschool.course.controller;

import org.itss.backtoschool.course.dto.request.TrafficRouteRequest;
import org.itss.backtoschool.course.dto.response.TrafficRouteResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface TrafficController {

    @PostMapping("/route-to-headquarters")
    ResponseEntity<TrafficRouteResponse> getRouteToHeadquarters(@RequestBody TrafficRouteRequest request);
}
