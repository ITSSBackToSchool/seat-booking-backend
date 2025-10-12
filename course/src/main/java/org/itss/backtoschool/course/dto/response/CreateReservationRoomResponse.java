package org.itss.backtoschool.course.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.itss.backtoschool.course.dto.ReservationRoomDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateReservationRoomResponse {
    private ReservationRoomDTO reservation;
}