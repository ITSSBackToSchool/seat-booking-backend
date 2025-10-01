package org.itss.backtoschool.course.dto.response;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter
public class WeatherResponse {
	private double latitude;
	private double longitude;
	private double generationtime_ms;
	private int utc_offset_seconds;
	private String timezone;
	private String timezone_abbreviation;
	private double elevation;
	private Map<String, String> current_units;
	private CurrentWeather current;

	@Getter
	@Setter
	public static class CurrentWeather {
		private String time;
		private int interval;
		private double temperature_2m;
		private int relative_humidity_2m;
		private double precipitation;
		private double surface_pressure;
	}
}
