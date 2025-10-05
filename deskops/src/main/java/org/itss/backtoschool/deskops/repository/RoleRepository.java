package org.itss.backtoschool.deskops.repository;

import org.itss.backtoschool.deskops.entities.Role;
import org.itss.backtoschool.deskops.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByUser(User user);
    java.util.Optional<Role> findByUserAndName(User user, String name);
}
