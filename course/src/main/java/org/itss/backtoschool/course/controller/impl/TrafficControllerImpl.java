package org.itss.backtoschool.course.controller.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.controller.TrafficController;
import org.itss.backtoschool.course.dto.response.TrafficIncident;
import org.itss.backtoschool.course.dto.response.TrafficRouteOption;
import org.itss.backtoschool.course.service.TrafficService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/traffic")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TrafficControllerImpl implements TrafficController {
	private final TrafficService trafficService;

	@Override
	public TrafficRouteOption getDirections(String start, boolean traffic, String travelMode) {
		return trafficService.getDirections(start,traffic,travelMode);
	}

	@Override
	public TrafficIncident getTrafficIncidents(String startBbox) {
		return trafficService.getTrafficIncidents(startBbox);
	}
}
