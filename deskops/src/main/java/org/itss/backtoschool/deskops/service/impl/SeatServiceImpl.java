package org.itss.backtoschool.deskops.service.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.deskops.dto.SeatDTO;
import org.itss.backtoschool.deskops.entities.ReservationStatus;
import org.itss.backtoschool.deskops.mapper.SeatMapper;
import org.itss.backtoschool.deskops.repository.ReservationRepository;
import org.itss.backtoschool.deskops.repository.SeatRepository;
import org.itss.backtoschool.deskops.service.SeatService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper;
    private final ReservationRepository reservationRepository;

    @Override
    public List<SeatDTO> getAllSeats() {
        var seats = seatRepository.findAll();
        return seatMapper.toDTOList(seats);
    }

    @Override
    public List<SeatDTO> getAvailableSeats(LocalDate date, Long buildingId) {
        var seats = seatRepository.findByBuildingId(buildingId);

        var availableSeats = seats.stream()
                .filter(seat -> !reservationRepository.existsBySeatIdAndReservationDateAndStatus(
                        seat.getId(), date, ReservationStatus.ACTIVE))
                .toList();

        return seatMapper.toDTOList(availableSeats);
    }
}
