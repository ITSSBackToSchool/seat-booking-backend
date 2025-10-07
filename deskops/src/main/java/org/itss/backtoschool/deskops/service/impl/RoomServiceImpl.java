package org.itss.backtoschool.deskops.service.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.deskops.dto.CreateRoomRequest;
import org.itss.backtoschool.deskops.dto.RoomDTO;
import org.itss.backtoschool.deskops.entities.Floor;
import org.itss.backtoschool.deskops.entities.Room;
import org.itss.backtoschool.deskops.entities.RoomType;
import org.itss.backtoschool.deskops.repository.FloorRepository;
import org.itss.backtoschool.deskops.repository.RoomRepository;
import org.itss.backtoschool.deskops.service.RoomService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final FloorRepository floorRepository;

    @Override
    public RoomDTO createRoom(CreateRoomRequest request) {
        Floor floor = floorRepository.findById(request.getFloorId())
                .orElseThrow(() -> new IllegalArgumentException("Floor not found: " + request.getFloorId()));

        Room room = new Room();
        room.setName(request.getName());
        room.setSeatCount(request.getSeatCount());
        room.setRoomType(RoomType.valueOf(request.getRoomType().name()));
        room.setFloor(floor);

        Room saved = roomRepository.save(room);

        RoomDTO dto = new RoomDTO();
        dto.setId(saved.getId());
        dto.setName(saved.getName());
        dto.setSeatCount(saved.getSeatCount());
        dto.setRoomType(saved.getRoomType().name());
        dto.setFloorId(floor.getId());

        return dto;
    }
}
