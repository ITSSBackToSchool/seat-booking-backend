package org.itss.backtoschool.course.service.impl;

import org.itss.backtoschool.course.dto.response.TrafficIncident;
import org.itss.backtoschool.course.dto.response.TrafficRouteOption;
import org.itss.backtoschool.course.service.TrafficService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class TrafficServiceImpl implements TrafficService {
	private final String directionsUrl = "https://atlas.microsoft.com/route/directions/json";
	private final String incidentsUrl = "https://atlas.microsoft.com/traffic/incident";

	private final String subscriptionKey = "DVMi3mwcpXW8LIFxEI8Q0OVPNi7PVtWUdTA94ybNUV9e5f3jtKDgJQQJ99BJAC5RqLJ9OuKzAAAgAZMPDWQ0";

	private final RestTemplate restTemplate = new RestTemplate();
	@Override
	public TrafficRouteOption getDirections(String start, boolean traffic, String travelMode) {
		String locatieITSS = "44.45050477369632,26.057440932852554";
		String url = UriComponentsBuilder.fromHttpUrl(directionsUrl)
				.queryParam("api-version",1.0)
				.queryParam("subscription-key",subscriptionKey)
				.queryParam("query",start + ":" + locatieITSS)
				.queryParam("traffic",traffic)
				.queryParam("computeTravelTimeFor","all")
				.queryParam("travelMode",travelMode)
				.toUriString();
		return restTemplate.getForObject(url,TrafficRouteOption.class);
	}

	@Override
	public TrafficIncident getTrafficIncidents(String startBbox) {
		String[] parts = startBbox.split(",");
		double startLat = Double.parseDouble(parts[0]);
		double startLon = Double.parseDouble(parts[1]);

		double destLat = 44.45050477369632;
		double destLon = 26.057440932852554;

		double minLat = Math.min(startLat, destLat);
		double minLon = Math.min(startLon, destLon);
		double maxLat = Math.max(startLat, destLat);
		double maxLon = Math.max(startLon, destLon);

		String bbox = minLon + "," + minLat + "," + maxLon + "," + maxLat;

		String url = UriComponentsBuilder.fromHttpUrl(incidentsUrl)
				.queryParam("api-version","2025-01-01")
				.queryParam("bbox",bbox)
				.queryParam("subscription-key", subscriptionKey)
				.toUriString();
		return restTemplate.getForObject(url,TrafficIncident.class);
	}
}
