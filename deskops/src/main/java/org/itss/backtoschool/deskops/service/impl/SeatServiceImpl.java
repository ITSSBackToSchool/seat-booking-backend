package org.itss.backtoschool.deskops.service.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.deskops.dto.SeatDTO;
import org.itss.backtoschool.deskops.mapper.SeatMapper;
import org.itss.backtoschool.deskops.repository.SeatRepository;
import org.itss.backtoschool.deskops.service.SeatService;
import org.springframework.stereotype.Service;

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
}
