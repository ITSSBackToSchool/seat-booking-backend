package org.itss.backtoschool.course.service;

import org.itss.backtoschool.course.dto.request.TrafficRouteRequest;
import org.itss.backtoschool.course.dto.response.TrafficRouteResponse;

public interface TrafficService {
    TrafficRouteResponse getRoutesToHeadquarters(TrafficRouteRequest request);
}
