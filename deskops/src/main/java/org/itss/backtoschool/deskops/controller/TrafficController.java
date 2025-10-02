package org.itss.backtoschool.deskops.controller;

import org.itss.backtoschool.deskops.dto.request.GetTrafficRequest;
import org.itss.backtoschool.deskops.dto.response.GetTrafficResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface TrafficController {
    @GetMapping
    ResponseEntity<GetTrafficResponse> getTrafficRecommendation(@RequestBody GetTrafficRequest request);
}
