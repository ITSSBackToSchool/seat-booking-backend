package org.itss.backtoschool.course.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class User extends CommonEntity {

    @Column(nullable = false, unique = true)
    private String userName;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String phone;

    @Column
    private String role;

    @Column(name = "home_address")
    private String homeAddress;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Reservation> reservations;
}
