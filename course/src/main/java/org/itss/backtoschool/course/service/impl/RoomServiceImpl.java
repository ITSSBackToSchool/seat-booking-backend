package org.itss.backtoschool.course.service.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.dto.RoomDTO;
import org.itss.backtoschool.course.entities.Room;
import org.itss.backtoschool.course.mapper.RoomMapper;
import org.itss.backtoschool.course.repository.RoomRepository;
import org.itss.backtoschool.course.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Override
    public List<RoomDTO> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return roomMapper.toDTOList(rooms);
    }

    @Override
    public List<RoomDTO> getRoomsByFloorId(Long floorId) {
        List<Room> rooms = roomRepository.findByfloorId(floorId);
        return roomMapper.toDTOList(rooms);
    }

    @Override
    public List<RoomDTO> getRoomsByBuildingId(Long buildingId) {
        List<Room> rooms = roomRepository.findByfloor_buildingId(buildingId);
        return roomMapper.toDTOList(rooms);
    }
}
