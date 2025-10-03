package org.itss.backtoschool.deskops.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.itss.backtoschool.deskops.entities.User;
import org.itss.backtoschool.deskops.repository.UserRepository;
import org.itss.backtoschool.deskops.service.UserService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRES_NEW)
    public User getOrCreateUser(Jwt jwt) {
        String auth0UserId = jwt.getSubject();

        if (auth0UserId == null || auth0UserId.isBlank()) {
            throw new IllegalArgumentException("JWT subject (user ID) cannot be null or empty");
        }

        String email = jwt.getClaimAsString("email");
        String name = jwt.getClaimAsString("name");

        if (email == null || email.isEmpty()) {
            email = auth0UserId + "@client.auth0.com";
        }
        if (name == null || name.isEmpty()) {
            name = "M2M Client: " + auth0UserId;
        }

        String finalEmail = email;
        String finalName = name;

        return userRepository.findByAuth0UserId(auth0UserId)
                .orElseGet(() -> {
                    log.info("Creating new user for Auth0 ID: {}", auth0UserId);
                    User newUser = new User();
                    newUser.setAuth0UserId(auth0UserId);
                    newUser.setEmail(finalEmail);
                    newUser.setName(finalName);
                    return userRepository.save(newUser);
                });
    }
}
