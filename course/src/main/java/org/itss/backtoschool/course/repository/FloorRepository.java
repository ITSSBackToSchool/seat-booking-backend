package org.itss.backtoschool.course.repository;

import org.itss.backtoschool.course.entities.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorRepository extends JpaRepository<Floor,Long> {
}
