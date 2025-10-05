package org.itss.backtoschool.course.repository;

import org.itss.backtoschool.course.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	List<Room> findAllByFloorNameAndFloor_Building_Name(String floorName,String buildingName);
}
