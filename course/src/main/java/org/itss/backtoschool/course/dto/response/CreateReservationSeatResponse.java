package org.itss.backtoschool.course.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.itss.backtoschool.course.dto.ReservationSeatDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateReservationSeatResponse {
    private List<ReservationSeatDTO> reservations;
}