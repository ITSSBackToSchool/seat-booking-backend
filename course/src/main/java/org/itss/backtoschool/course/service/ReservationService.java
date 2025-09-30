package org.itss.backtoschool.course.service;

import org.itss.backtoschool.course.dto.request.CreateReservationRequest;
import org.itss.backtoschool.course.dto.response.CreateReservationResponse;



public interface ReservationService {
    CreateReservationResponse createReservation(CreateReservationRequest response);

}
