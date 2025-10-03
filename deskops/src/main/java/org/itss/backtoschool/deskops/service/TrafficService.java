package org.itss.backtoschool.deskops.service;

import org.itss.backtoschool.deskops.dto.TrafficDTO;
import org.itss.backtoschool.deskops.dto.request.GetTrafficRequest;
import org.itss.backtoschool.deskops.dto.response.GetTrafficResponse;
import org.itss.backtoschool.deskops.entities.User;
import org.springframework.security.oauth2.jwt.Jwt;

public interface TrafficService {
    GetTrafficResponse getTrafficRecommendation(Long buildingId, Jwt jwt);

    TrafficDTO getTrafficForUser(User user, Long buildingId);
}

