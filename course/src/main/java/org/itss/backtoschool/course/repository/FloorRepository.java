package org.itss.backtoschool.course.repository;

import org.itss.backtoschool.course.entities.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorRepository extends JpaRepository<Floor,Long> {
    List<Floor> findByBuildingId(Long buildingId);
}
