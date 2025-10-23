package org.itss.backtoschool.course.service.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.dto.RoomDTO;
import org.itss.backtoschool.course.entities.Room;
import org.itss.backtoschool.course.mapper.RoomMapper;
import org.itss.backtoschool.course.repository.ReservationRepository;
import org.itss.backtoschool.course.repository.RoomRepository;
import org.itss.backtoschool.course.service.RoomService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final ReservationRepository reservationRepository;

    @Override
    public List<RoomDTO> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return roomMapper.toDTOList(rooms);
    }

    @Override
    public List<RoomDTO> getRoomsByFloorId(Long floorId) {
        List<Room> rooms = roomRepository.findByFloorId(floorId);
        return roomMapper.toDTOList(rooms);
    }

    @Override
    public List<RoomDTO> getRoomsByBuildingId(Long buildingId) {
        List<Room> rooms = roomRepository.findByFloorBuildingId(buildingId);
        return roomMapper.toDTOList(rooms);
    }

    @Override
    public List<RoomDTO> getRoomsByFloorIdWithAvailability(Long floorId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        List<Room> rooms = roomRepository.findByFloorId(floorId);
        return rooms.stream()
                .map(room -> {
                    RoomDTO dto = roomMapper.toDTO(room);
                    boolean isAvailable = !reservationRepository.hasOverlappingRoomReservation(
                            room.getId(), date, startTime, endTime);
                    dto.setIsAvailable(isAvailable);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDTO> getRoomsByBuildingIdWithAvailability(Long buildingId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        List<Room> rooms = roomRepository.findByFloorBuildingId(buildingId);
        return rooms.stream()
                .map(room -> {
                    RoomDTO dto = roomMapper.toDTO(room);
                    boolean isAvailable = !reservationRepository.hasOverlappingRoomReservation(
                            room.getId(), date, startTime, endTime);
                    dto.setIsAvailable(isAvailable);
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
