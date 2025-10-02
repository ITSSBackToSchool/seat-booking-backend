package org.itss.backtoschool.course.service.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.dto.SeatDTO;
import org.itss.backtoschool.course.entities.ReservationStatus;
import org.itss.backtoschool.course.mapper.SeatMapper;
import org.itss.backtoschool.course.repository.SeatRepository;
import org.itss.backtoschool.course.service.SeatService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper;

    @Override
    public List<SeatDTO> getAllSeats() {
        var seats = seatRepository.findAll();
        return seatMapper.toDTOList(seats);
    }

    @Override
    public List<SeatDTO> getAvailableSeats(LocalDateTime dateStart, LocalDateTime dateEnd, ReservationStatus status) {
        return seatRepository.findAvailableSeats(dateStart, dateEnd, status).stream().map(seatMapper::toDTO).toList();
    }
}
