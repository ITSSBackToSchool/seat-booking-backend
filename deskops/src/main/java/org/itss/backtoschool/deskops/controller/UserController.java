package org.itss.backtoschool.deskops.controller;

import org.itss.backtoschool.deskops.dto.AssignRoleRequest;
import org.itss.backtoschool.deskops.dto.RoleDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

public interface UserController {

    @PostMapping("/{userId}/roles")
    ResponseEntity<RoleDTO> assignRole(
            @PathVariable Long userId,
            @Valid @RequestBody AssignRoleRequest request
    );
}
