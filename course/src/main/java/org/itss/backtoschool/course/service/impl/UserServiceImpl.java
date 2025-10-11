package org.itss.backtoschool.course.service.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.course.dto.UserDTO;
import org.itss.backtoschool.course.entities.User;
import org.itss.backtoschool.course.mapper.UserMapper;
import org.itss.backtoschool.course.repository.UserRepository;
import org.itss.backtoschool.course.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<UserDTO> findByEmail(String email) {
        return userRepository.findByEmail(email).map(userMapper::toDTO);
    }

    @Override
    public Optional<UserDTO> findByUserName(String userName) {
        return userRepository.findByUserName(userName).map(userMapper::toDTO);
    }

    @Override
    public UserDTO register(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }
}
