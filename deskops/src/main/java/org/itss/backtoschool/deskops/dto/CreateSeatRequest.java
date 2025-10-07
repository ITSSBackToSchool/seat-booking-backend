package org.itss.backtoschool.deskops.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSeatRequest {
    @NotBlank
    private String seatNumber;

    @NotNull
    private Long roomId;
}
