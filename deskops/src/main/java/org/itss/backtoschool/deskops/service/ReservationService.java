package org.itss.backtoschool.deskops.service;

import org.itss.backtoschool.deskops.dto.request.CreateReservationRequest;
import org.itss.backtoschool.deskops.dto.request.UpdateReservationRequest;
import org.itss.backtoschool.deskops.dto.response.CreateReservationResponse;
import org.springframework.security.oauth2.jwt.Jwt;



public interface ReservationService {
    CreateReservationResponse createReservations(CreateReservationRequest request, Jwt jwt);
    CreateReservationResponse getAllReservations(Jwt jwt);
    CreateReservationResponse getReservation(Long reservationId, Jwt jwt);
    void deleteReservation(Long reservationId, Jwt jwt);
    CreateReservationResponse updateReservation(Long reservationId, UpdateReservationRequest request, Jwt jwt);
}
