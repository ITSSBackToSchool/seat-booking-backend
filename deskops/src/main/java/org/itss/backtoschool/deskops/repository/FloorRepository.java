package org.itss.backtoschool.deskops.repository;

import org.itss.backtoschool.deskops.entities.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorRepository extends JpaRepository<Floor,Long> {
}
