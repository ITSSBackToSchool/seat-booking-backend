package org.itss.backtoschool.deskops.repository;

import org.itss.backtoschool.deskops.entities.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
    Optional<WeatherData> findByLocationIdAndDate(Long locationId, LocalDate date);

    List<WeatherData> findByLocationIdAndDateBetween(Long locationId, LocalDate startDate, LocalDate endDate);
}
