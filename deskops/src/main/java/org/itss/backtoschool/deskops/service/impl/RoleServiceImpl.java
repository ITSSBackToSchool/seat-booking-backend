package org.itss.backtoschool.deskops.service.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.deskops.entities.Permission;
import org.itss.backtoschool.deskops.entities.Role;
import org.itss.backtoschool.deskops.entities.RoleState;
import org.itss.backtoschool.deskops.entities.User;
import org.itss.backtoschool.deskops.repository.RoleRepository;
import org.itss.backtoschool.deskops.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role assignRoleToUser(User user, String roleName) {
        return roleRepository.findByUserAndName(user, roleName)
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setUser(user);
                    role.setName(roleName);
                    // hardcoded defaults for HR role
                    if ("HR".equalsIgnoreCase(roleName)) {
                        role.setState(RoleState.HR);
            role.getPermissions().addAll(Arrays.asList(
                Permission.CREATE_SEAT,
                Permission.VIEW_SEATS,
                Permission.ASSIGN_ROLE,
                Permission.MANAGE_USERS
            ));
                    }

                    return roleRepository.save(role);
                });
    }
}
