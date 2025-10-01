package org.itss.backtoschool.deskops.repository;

import org.itss.backtoschool.deskops.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
