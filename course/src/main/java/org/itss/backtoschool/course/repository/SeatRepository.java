package org.itss.backtoschool.course.repository;

import org.itss.backtoschool.course.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query("SELECT s FROM Seat s WHERE s.room.id = :roomId")
    List<Seat> findByRoomId(@Param("roomId") Long roomId);
}
