package org.itss.backtoschool.deskops.service;

import org.itss.backtoschool.deskops.dto.request.CreateReservationRequest;
import org.itss.backtoschool.deskops.dto.request.UpdateReservationRequest;
import org.itss.backtoschool.deskops.dto.response.CreateReservationResponse;



public interface ReservationService {
    CreateReservationResponse createReservations(CreateReservationRequest request);
    CreateReservationResponse getAllReservations();
    CreateReservationResponse getReservation(Long reservationId);
    void deleteReservation(Long reservationId);
    CreateReservationResponse updateReservation(Long reservationId, UpdateReservationRequest request);
}
