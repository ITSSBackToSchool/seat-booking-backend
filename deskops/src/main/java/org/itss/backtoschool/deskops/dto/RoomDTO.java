package org.itss.backtoschool.deskops.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private Long id;
    private String name;
    private Integer seatCount;
    private String roomType;
    private Long floorId;
}
