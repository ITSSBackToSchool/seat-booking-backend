package org.itss.backtoschool.course.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reservations")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class Reservation extends CommonEntity{

    @Column(nullable = false)
    private LocalDateTime reservationDateStart;

    @Column(nullable = false)
    private LocalDateTime reservationDateEnd;

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
