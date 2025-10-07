package org.itss.backtoschool.deskops.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoomRequest {
    @NotBlank
    private String name;

    @NotNull
    private Long floorId;

    @NotNull
    private RoomTypeRequest roomType;

    @Min(0)
    private Integer seatCount;

    public enum RoomTypeRequest { DESK_ROOM, CONFERENCE_ROOM, RECREATIONAL, COLLABORATIVE }
}
