package org.itss.backtoschool.course.controller;

import org.itss.backtoschool.course.dto.response.TrafficIncident;
import org.itss.backtoschool.course.dto.response.TrafficRouteOption;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface TrafficController {

	@GetMapping("/directions")
	TrafficRouteOption getDirections(@RequestParam String start, @RequestParam boolean traffic, String travelMode);

	@GetMapping("/incidents")
	TrafficIncident getTrafficIncidents(String startBbox);

}
