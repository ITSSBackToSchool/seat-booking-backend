package org.itss.backtoschool.deskops.controller.impl;

import com.auth0.exception.Auth0Exception;
import com.auth0.json.mgmt.roles.Role;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.itss.backtoschool.deskops.dto.Auth0RoleAssignmentRequest;
import org.itss.backtoschool.deskops.service.Auth0ManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller for managing Auth0 roles programmatically.
 * Only accessible by users with ASSIGN_ROLE permission (ADMIN).
 */
@Slf4j
@RestController
@RequestMapping("/api/auth0/roles")
@RequiredArgsConstructor
public class Auth0RoleManagementController {

    private final Auth0ManagementService auth0ManagementService;

    /**
     * Assign a role to a user in Auth0.
     *
     * @param request Contains auth0UserId and roleName (EMPLOYEE, HR, or ADMIN)
     * @return Success message
     */
    @PostMapping("/assign")
    @PreAuthorize("hasAuthority('ASSIGN_ROLE')")
    public ResponseEntity<Map<String, String>> assignRole(@Valid @RequestBody Auth0RoleAssignmentRequest request) {
        try {
            log.info("Admin assigning role '{}' to Auth0 user '{}'", request.getRoleName(), request.getAuth0UserId());

            auth0ManagementService.assignRoleToUser(request.getAuth0UserId(), request.getRoleName());

            Map<String, String> response = new HashMap<>();
            response.put("message", "Role '" + request.getRoleName() + "' assigned successfully to user " + request.getAuth0UserId());
            response.put("userId", request.getAuth0UserId());
            response.put("role", request.getRoleName());

            return ResponseEntity.ok(response);

        } catch (Auth0Exception e) {
            log.error("Failed to assign role in Auth0: {}", e.getMessage(), e);
            Map<String, String> error = new HashMap<>();
            error.put("error", "Auth0 API error: " + e.getMessage());
            return ResponseEntity.status(500).body(error);
        }
    }

    /**
     * Remove a role from a user in Auth0.
     *
     * @param request Contains auth0UserId and roleName
     * @return Success message
     */
    @PostMapping("/remove")
    @PreAuthorize("hasAuthority('ASSIGN_ROLE')")
    public ResponseEntity<Map<String, String>> removeRole(@Valid @RequestBody Auth0RoleAssignmentRequest request) {
        try {
            log.info("Admin removing role '{}' from Auth0 user '{}'", request.getRoleName(), request.getAuth0UserId());

            auth0ManagementService.removeRoleFromUser(request.getAuth0UserId(), request.getRoleName());

            Map<String, String> response = new HashMap<>();
            response.put("message", "Role '" + request.getRoleName() + "' removed successfully from user " + request.getAuth0UserId());
            response.put("userId", request.getAuth0UserId());
            response.put("role", request.getRoleName());

            return ResponseEntity.ok(response);

        } catch (Auth0Exception e) {
            log.error("Failed to remove role in Auth0: {}", e.getMessage(), e);
            Map<String, String> error = new HashMap<>();
            error.put("error", "Auth0 API error: " + e.getMessage());
            return ResponseEntity.status(500).body(error);
        }
    }

    /**
     * Get all roles assigned to a user in Auth0.
     *
     * @param auth0UserId The Auth0 user ID (e.g., "auth0|123456")
     * @return List of roles
     */
    @GetMapping("/user/{auth0UserId}")
    @PreAuthorize("hasAuthority('ASSIGN_ROLE')")
    public ResponseEntity<?> getUserRoles(@PathVariable String auth0UserId) {
        try {
            log.info("Admin fetching roles for Auth0 user '{}'", auth0UserId);

            List<Role> roles = auth0ManagementService.getUserRoles(auth0UserId);

            Map<String, Object> response = new HashMap<>();
            response.put("userId", auth0UserId);
            response.put("roles", roles.stream().map(Role::getName).toList());
            response.put("roleCount", roles.size());

            return ResponseEntity.ok(response);

        } catch (Auth0Exception e) {
            log.error("Failed to fetch user roles from Auth0: {}", e.getMessage(), e);
            Map<String, String> error = new HashMap<>();
            error.put("error", "Auth0 API error: " + e.getMessage());
            return ResponseEntity.status(500).body(error);
        }
    }
}
