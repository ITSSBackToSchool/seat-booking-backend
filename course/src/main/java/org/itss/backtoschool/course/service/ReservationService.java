package org.itss.backtoschool.course.service;

import org.itss.backtoschool.course.dto.ReservationDTO;
import org.itss.backtoschool.course.dto.request.CreateReservationRequest;
import org.itss.backtoschool.course.dto.response.CreateReservationResponse;
import org.itss.backtoschool.course.entities.Seat;

import java.util.List;


public interface ReservationService {
    CreateReservationResponse createReservations(CreateReservationRequest request);
    List<ReservationDTO> findAll();
    List<ReservationDTO> findReservationsByUserId(Long userId);
    String cancelResevation(Long reservationId);
    String completeReservations();
}
