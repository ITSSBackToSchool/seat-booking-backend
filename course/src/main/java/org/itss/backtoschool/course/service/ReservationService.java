package org.itss.backtoschool.course.service;

import org.itss.backtoschool.course.dto.ReservationDTO;
import org.itss.backtoschool.course.dto.request.CreateReservationRequest;
import org.itss.backtoschool.course.dto.request.CreateReservationRoomRequest;
import org.itss.backtoschool.course.dto.response.CreateReservationResponse;
import org.itss.backtoschool.course.dto.response.TimeSlot;
import org.itss.backtoschool.course.entities.Reservation;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;


public interface ReservationService {
    CreateReservationResponse createReservations(CreateReservationRequest request);
    List<ReservationDTO> findAll();
    List<ReservationDTO> findReservationsByUserId(Long userId);
    String cancelResevation(Long reservationId);
    @Scheduled(cron = "0 0 0 * * *")
    String completeReservations();
    ReservationDTO createRoomReservation(CreateReservationRoomRequest request);
    List<TimeSlot> findByRoom_IdAndReservationDateStartLessThanAndReservationDateEndGreaterThan(
            Long roomId, LocalDateTime dateEnd, LocalDateTime dateStart
    );
}
