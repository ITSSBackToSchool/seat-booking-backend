package org.itss.backtoschool.deskops.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.itss.backtoschool.deskops.dto.ReservationDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateReservationResponse {
    private List<ReservationDTO> reservations;
}
