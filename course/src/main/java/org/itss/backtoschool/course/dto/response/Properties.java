package org.itss.backtoschool.course.dto.response;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties {
	private String incidentType;
	private String startTime;
	private String endTime;
	private String description;
	private int severity;
	private boolean isRoadClosed;
	private String title;
	private boolean isTrafficJam;
	private int delay;
	private Geometry endPoint;
}