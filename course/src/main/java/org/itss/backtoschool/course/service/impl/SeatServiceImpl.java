package org.itss.backtoschool.course.service.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.dto.SeatAvailabilityDTO;
import org.itss.backtoschool.course.dto.SeatDTO;
import org.itss.backtoschool.course.entities.Reservation;
import org.itss.backtoschool.course.entities.Room;
import org.itss.backtoschool.course.entities.RoomType;
import org.itss.backtoschool.course.entities.Seat;
import org.itss.backtoschool.course.mapper.SeatMapper;
import org.itss.backtoschool.course.repository.ReservationRepository;
import org.itss.backtoschool.course.repository.RoomRepository;
import org.itss.backtoschool.course.repository.SeatRepository;
import org.itss.backtoschool.course.service.SeatService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public List<SeatDTO> getAllSeats() {
        var seats = seatRepository.findAll();
        return seatMapper.toDTOList(seats);
    }

    @Override
    public List<SeatDTO> getSeatsByRoomId(Long roomId) {
        var seats = seatRepository.findByRoomId(roomId);
        return seatMapper.toDTOList(seats);
    }

    @Override
    public List<SeatAvailabilityDTO> getAvailableSeats(
            Long buildingId,
            Long floorId,
            Long roomId,
            LocalDate date,
            LocalTime startTime,
            LocalTime endTime
    ) {

        List<Seat> seats;

        if (roomId != null) {
            seats = seatRepository.findByRoomId(roomId);
        } else if (floorId != null) {
            var rooms = roomRepository.findByFloorId(floorId);

            seats = rooms.stream()
                    .filter(room -> room.getRoomType() == RoomType.DESK_ROOM)
                    .flatMap(room -> room.getSeats().stream())
                    .toList();
        } else {
            seats = seatRepository.findAll();
        }


        List<SeatAvailabilityDTO> result = new ArrayList<>();

        for (Seat seat : seats) {

            List<Reservation> overlapping = reservationRepository.findOverlappingSeatReservations(
                    seat.getId(),
                    date,
                    startTime,
                    endTime
            );

            SeatAvailabilityDTO dto = SeatAvailabilityDTO.builder()
                    .id(seat.getId())
                    .seatNumber(seat.getSeatNumber())
                    .roomName(seat.getRoom().getName())
                    .floorName(seat.getRoom().getFloor().getName())
                    .buildingName(seat.getRoom().getFloor().getBuilding().getName())
                    .isAvailable(overlapping.isEmpty())
                    .build();


            if (!overlapping.isEmpty()) {
                Reservation reservation = overlapping.get(0);
                dto.setReservedBy(reservation.getUsers().getUserName());
                dto.setReservedTime(reservation.getStartTime() + " - " + reservation.getEndTime());
            }

            result.add(dto);
        }

        return result;
    }
}
