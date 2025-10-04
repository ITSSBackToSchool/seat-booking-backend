package org.itss.backtoschool.course.service.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.dto.SeatDTO;
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
    public List<SeatDTO> findAvailableSeatsByFloorAndReservationTime(Long floorId, LocalDateTime dateStart, LocalDateTime dateEnd) {
        List<SeatDTO> availableSeats = seatRepository.findAvailableSeatsByFloorAndReservationTime(floorId,dateStart,dateEnd).stream().map(seatMapper::toDTO).toList();
        List<SeatDTO> seatsByFloor = seatRepository.findSeatsByFloor_Id(floorId).stream().map(seatMapper::toDTO).toList();

        for(SeatDTO seatDTO : seatsByFloor){
            SeatDTO foundSeat = availableSeats.stream().filter(seat -> seat.getId().equals(seatDTO.getId())).findFirst().orElse(null);
	        seatDTO.setOccupied(foundSeat == null);
        }
        return seatsByFloor;
    }

}
