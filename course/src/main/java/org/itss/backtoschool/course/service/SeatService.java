package org.itss.backtoschool.course.service;

import org.itss.backtoschool.course.dto.SeatDTO;
import org.itss.backtoschool.course.entities.ReservationStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SeatService {
    List<SeatDTO> getAllSeats();
    List<SeatDTO> getAvailableSeats(LocalDateTime dateStart, LocalDateTime dateEnd, ReservationStatus status);
}
