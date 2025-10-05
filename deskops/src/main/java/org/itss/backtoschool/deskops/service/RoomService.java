package org.itss.backtoschool.deskops.service;

import org.itss.backtoschool.deskops.dto.CreateRoomRequest;
import org.itss.backtoschool.deskops.dto.RoomDTO;

public interface RoomService {
    RoomDTO createRoom(CreateRoomRequest request);
}
