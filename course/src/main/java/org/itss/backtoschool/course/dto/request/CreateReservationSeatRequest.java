package org.itss.backtoschool.course.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservationSeatRequest {
    private Long userId;
    private List<Long> seatId;
    private LocalDate reservationDate;
   private LocalTime startTime;
   private LocalTime endTime;

}
