package org.itss.backtoschool.course.dto.response;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Feature {
	private String type;
	private long id;
	private Geometry geometry;
	private Properties properties;
}