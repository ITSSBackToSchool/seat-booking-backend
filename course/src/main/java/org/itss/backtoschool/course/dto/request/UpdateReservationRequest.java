package org.itss.backtoschool.course.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateReservationRequest {
    private LocalDate reservationDate;
    private LocalTime startTime;
    private LocalTime endTime;
}

