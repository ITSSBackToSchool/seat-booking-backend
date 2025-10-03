package org.itss.backtoschool.course.service;

import org.itss.backtoschool.course.dto.response.TrafficIncident;
import org.itss.backtoschool.course.dto.response.TrafficRouteOption;

public interface TrafficService {
	TrafficRouteOption getDirections(String start, boolean traffic, String travelMode);
	TrafficIncident getTrafficIncidents(String startBbox);
}
