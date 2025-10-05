package org.itss.backtoschool.deskops.controller.impl;

import lombok.RequiredArgsConstructor;
import org.itss.backtoschool.deskops.controller.UserController;
import org.itss.backtoschool.deskops.dto.AssignRoleRequest;
import org.itss.backtoschool.deskops.dto.RoleDTO;
import org.itss.backtoschool.deskops.entities.Role;
import org.itss.backtoschool.deskops.entities.User;
import org.itss.backtoschool.deskops.repository.UserRepository;
import org.itss.backtoschool.deskops.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Override
    public ResponseEntity<RoleDTO> assignRole(Long userId, AssignRoleRequest request) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOpt.get();
        Role role = roleService.assignRoleToUser(user, request.getRoleName());

        RoleDTO dto = new RoleDTO();
        dto.setId(role.getId());
        dto.setUserId(user.getId());
        dto.setName(role.getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
