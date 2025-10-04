package org.itss.backtoschool.course.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateReservationRoomRequest {
	private Long userId;
	private Long roomdId;
	private LocalDateTime reservationDateStart;
	private LocalDateTime reservationDateEnd;
}
