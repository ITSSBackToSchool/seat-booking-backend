package org.itss.backtoschool.course.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@Table(name = "reservations")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Reservation extends CommonEntity {

    @Column(nullable = false)
    private LocalDate reservationDate;

    @Column(nullable = false, name = "start_time")
    private LocalTime startTime;

    @Column(nullable = false, name = "end_time")
    private LocalTime endTime;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id")
    @ToString.Exclude
    private Seat seat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    @ToString.Exclude
    private Room room;
}
