package org.itss.backtoschool.course.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomAvailability {
	private Long roomId;
	private LocalDate date;
	private List<TimeSlot> timeSlots;
}
