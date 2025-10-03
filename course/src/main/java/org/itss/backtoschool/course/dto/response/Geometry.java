package org.itss.backtoschool.course.dto.response;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry {
	private String type;
	private List<Double> coordinates;
}