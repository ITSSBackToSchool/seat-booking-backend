package org.itss.backtoschool.deskops.repository;

import org.itss.backtoschool.deskops.entities.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building,Long> {
}
