package org.itss.backtoschool.deskops.repository;

import org.itss.backtoschool.deskops.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByBuildingId(Long buildingId);
}
