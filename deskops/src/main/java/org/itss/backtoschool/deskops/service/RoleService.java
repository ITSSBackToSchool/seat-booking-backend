package org.itss.backtoschool.deskops.service;

import org.itss.backtoschool.deskops.entities.Role;
import org.itss.backtoschool.deskops.entities.User;

public interface RoleService {
    Role assignRoleToUser(User user, String roleName);
}
