package org.itss.backtoschool.deskops.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "reservations")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class Reservation extends CommonEntity{

    @Column(nullable = false)
    private LocalDate reservationDate;

    // Time slot fields - null for DESK_ROOM (all-day), required for CONFERENCE_ROOM/COLLABORATIVE/RECREATIONAL
    @Column(nullable = true)
    private LocalTime startTime;

    @Column(nullable = true)
    private LocalTime endTime;

    // Flag for booking entire conference room - all seats in room will be reserved
    @Column(nullable = false)
    @Builder.Default
    private Boolean bookEntireRoom = false;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id",  nullable = false)
    @ToString.Exclude
    private Seat seat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",  nullable = false)
    @ToString.Exclude
    private User user;
}
