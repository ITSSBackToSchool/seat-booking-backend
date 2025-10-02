package org.itss.backtoschool.deskops.service;

import org.itss.backtoschool.deskops.dto.SeatDTO;

import java.time.LocalDate;
import java.util.List;

public interface SeatService {
    List<SeatDTO> getAllSeats();
    List<SeatDTO> getAvailableSeats(LocalDate date, Long buildingId);
}
