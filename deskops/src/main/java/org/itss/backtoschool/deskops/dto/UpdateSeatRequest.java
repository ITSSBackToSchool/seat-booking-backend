package org.itss.backtoschool.deskops.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSeatRequest {
    private String seatNumber;
    private Long roomId;
}
