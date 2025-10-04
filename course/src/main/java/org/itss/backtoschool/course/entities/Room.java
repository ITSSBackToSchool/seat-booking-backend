package org.itss.backtoschool.course.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "room")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Room extends CommonEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer seatCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_id",  nullable = false)
    @ToString.Exclude
    private Floor floor;

    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;
}
