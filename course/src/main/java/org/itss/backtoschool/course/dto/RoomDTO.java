package org.itss.backtoschool.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
	private Long roomId;
	private Integer seatCount;
	private String name;
	private String buildingName;
	private String floorName;
}
