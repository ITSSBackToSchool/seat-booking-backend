package org.itss.backtoschool.deskops.service;

import org.itss.backtoschool.deskops.dto.request.CreateReservationRequest;
import org.itss.backtoschool.deskops.dto.response.CreateReservationResponse;



public interface ReservationService {
    CreateReservationResponse createReservations(CreateReservationRequest request);
}
