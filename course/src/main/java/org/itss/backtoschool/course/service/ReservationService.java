package org.itss.backtoschool.course.service;

import org.itss.backtoschool.course.dto.ReservationSeatDTO;
import org.itss.backtoschool.course.dto.ReservationRoomDTO;
import org.itss.backtoschool.course.dto.UserReservationDTO;
import org.itss.backtoschool.course.dto.request.CreateReservationSeatRequest;
import org.itss.backtoschool.course.dto.request.CreateReservationRoomRequest;
import org.itss.backtoschool.course.dto.request.UpdateReservationRequest;
import org.itss.backtoschool.course.dto.response.CreateReservationSeatResponse;
import org.itss.backtoschool.course.dto.response.CreateReservationRoomResponse;

import java.util.List;

public interface ReservationService {

    CreateReservationSeatResponse createReservationsForSeats(CreateReservationSeatRequest request);

    CreateReservationRoomResponse createReservationForRoom(CreateReservationRoomRequest request);

    List<ReservationSeatDTO> getAllSeatReservations();

    List<ReservationRoomDTO> getAllRoomReservations();
    
    List<UserReservationDTO> getUserReservations(Long userId);
    
    UserReservationDTO updateReservation(Long reservationId, UpdateReservationRequest request);
    
    void cancelReservation(Long reservationId);
}
