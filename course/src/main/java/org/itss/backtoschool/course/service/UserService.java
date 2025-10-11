package org.itss.backtoschool.course.service;

import org.itss.backtoschool.course.dto.UserDTO;

import java.util.Optional;

public interface UserService {
    Optional<UserDTO> findByEmail(String email);
    Optional<UserDTO> findByUserName(String userName);
    UserDTO register(UserDTO userDTO);
}
