package org.itss.backtoschool.deskops.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Auth0RoleAssignmentRequest {

    @NotBlank(message = "Auth0 user ID is required")
    private String auth0UserId;

    @NotBlank(message = "Role name is required")
    @Pattern(regexp = "EMPLOYEE|HR|ADMIN", message = "Role must be EMPLOYEE, HR, or ADMIN")
    private String roleName;
}
