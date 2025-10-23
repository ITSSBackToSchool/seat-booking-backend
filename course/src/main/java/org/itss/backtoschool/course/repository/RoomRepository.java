package org.itss.backtoschool.course.repository;

import org.itss.backtoschool.course.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT r FROM Room r JOIN FETCH r.floor WHERE r.floor.id = :floorId")
    List<Room> findByFloorId(@Param("floorId") Long floorId);

    @Query("SELECT r FROM Room r JOIN FETCH r.floor WHERE r.floor.building.id = :buildingId")
    List<Room> findByFloorBuildingId(@Param("buildingId") Long buildingId);
}
