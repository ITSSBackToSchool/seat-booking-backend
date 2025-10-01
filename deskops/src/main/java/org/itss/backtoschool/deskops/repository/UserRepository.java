package org.itss.backtoschool.deskops.repository;

import org.itss.backtoschool.deskops.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
