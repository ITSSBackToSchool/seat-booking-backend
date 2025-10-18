package org.itss.backtoschool.course.repository;

import org.itss.backtoschool.course.entities.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building,Long> {
}
