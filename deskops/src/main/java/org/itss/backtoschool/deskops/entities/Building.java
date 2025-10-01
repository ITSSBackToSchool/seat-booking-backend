package org.itss.backtoschool.deskops.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "building")
@EqualsAndHashCode(callSuper = true)
public class Building extends CommonEntity{

    @Column(nullable = false, unique = true)
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Floor> floors;
}
