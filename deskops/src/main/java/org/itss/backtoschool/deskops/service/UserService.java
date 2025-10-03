package org.itss.backtoschool.deskops.service;

import org.itss.backtoschool.deskops.entities.User;
import org.springframework.security.oauth2.jwt.Jwt;

public interface UserService {
    User getOrCreateUser(Jwt jwt);
}
