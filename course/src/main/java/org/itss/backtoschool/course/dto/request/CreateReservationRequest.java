package org.itss.backtoschool.course.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservationRequest {
    private Long userId;
    private List<Long> seatIds;
    private LocalDate reservationDate;

}
