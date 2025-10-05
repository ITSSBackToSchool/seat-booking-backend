package org.itss.backtoschool.deskops.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends CommonEntity{

    @Column(unique = true, nullable = false)
    private String auth0UserId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String homeStreet;

    @Column
    private Integer homeStreetNumber;

    @Column
    private String homeCity;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Role> roles;

    /**
     * Check whether this user has the given permission via any ACTIVE role.
     */
    public boolean hasPermission(Permission permission) {
        if (roles == null) return false;
        return roles.stream().anyMatch(role -> role != null && role.hasPermission(permission));
    }
}
