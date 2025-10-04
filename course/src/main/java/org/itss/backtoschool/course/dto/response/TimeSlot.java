package org.itss.backtoschool.course.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeSlot {
	private LocalDateTime start;
	private LocalDateTime end;
	private boolean available;
}
