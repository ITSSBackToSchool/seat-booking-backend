package org.itss.backtoschool.deskops.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReservationRequest {
    private Long seatId;
    private LocalDate reservationDate;
}
