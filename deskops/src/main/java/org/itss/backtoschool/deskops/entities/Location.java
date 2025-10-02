package org.itss.backtoschool.deskops.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "location")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Location extends CommonEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", nullable = false, unique = true)
    @ToString.Exclude
    private Building building;

    @Column(nullable = false)
    private String city;

    private String district;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private String address;
}
