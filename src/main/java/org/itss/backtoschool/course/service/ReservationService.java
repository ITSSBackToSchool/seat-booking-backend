package org.itss.backtoschool.course.service;

import org.itss.backtoschool.course.dto.request.CreateReservationRoomRequest;
import org.itss.backtoschool.course.dto.response.CreateReservationRoomResponse;


public interface ReservationService {
    CreateReservationRoomResponse createReservations(CreateReservationRoomRequest request);
}
