package org.itss.backtoschool.deskops.controller.impl;

import org.itss.backtoschool.deskops.controller.TrafficController;
import org.itss.backtoschool.deskops.dto.request.GetTrafficRequest;
import org.itss.backtoschool.deskops.dto.response.GetTrafficResponse;
import org.springframework.http.ResponseEntity;

public class TrafficControllerImpl implements TrafficController {

    @Override
    public ResponseEntity<GetTrafficResponse> getTrafficRecommendation(GetTrafficRequest request) {
        return null;
    }
}
