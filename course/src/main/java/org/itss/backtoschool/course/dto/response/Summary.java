package org.itss.backtoschool.course.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Summary {
	private long lengthInMeters;
	private long travelTimeInSeconds;
	private long trafficDelayInSeconds;
	private long trafficLengthInMeters;
	private String departureTime;
	private String arrivalTime;
	private long noTrafficTravelTimeInSeconds;
	private long historicTrafficTravelTimeInSeconds;
	private long liveTrafficIncidentsTravelTimeInSeconds;
}
