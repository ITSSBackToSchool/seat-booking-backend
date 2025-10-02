package org.itss.backtoschool.deskops.service;

import org.itss.backtoschool.deskops.dto.request.GetTrafficRequest;
import org.itss.backtoschool.deskops.dto.response.GetTrafficResponse;

public interface TrafficService {
    GetTrafficResponse getTrafficRecommendation(String street, int streetNumber, String city);

}

