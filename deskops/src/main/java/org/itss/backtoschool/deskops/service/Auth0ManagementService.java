package org.itss.backtoschool.deskops.service;

import com.auth0.client.auth.AuthAPI;
import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.TokenHolder;
import com.auth0.json.mgmt.roles.Role;
import com.auth0.net.TokenRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.itss.backtoschool.deskops.config.Auth0ManagementProperties;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Service for managing Auth0 roles programmatically via the Auth0 Management API.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class Auth0ManagementService {

    private final Auth0ManagementProperties properties;

    /**
     * Get an authenticated ManagementAPI client.
     */
    private ManagementAPI getManagementAPI() throws Auth0Exception {
        // Authenticate to get an access token
        AuthAPI authAPI = AuthAPI.newBuilder(
                properties.getManagement().getDomain(),
                properties.getManagement().getClientId(),
                properties.getManagement().getClientSecret()
        ).build();

        TokenRequest tokenRequest = authAPI.requestToken("https://" + properties.getManagement().getDomain() + "/api/v2/");
        TokenHolder holder = tokenRequest.execute().getBody();

        return ManagementAPI.newBuilder(
                properties.getManagement().getDomain(),
                holder.getAccessToken()
        ).build();
    }

    /**
     * Assign a role to a user by Auth0 user ID.
     *
     * @param auth0UserId The Auth0 user ID (e.g., "auth0|123456")
     * @param roleName    Role name: "EMPLOYEE", "HR", or "ADMIN"
     * @throws Auth0Exception if the API call fails
     */
    public void assignRoleToUser(String auth0UserId, String roleName) throws Auth0Exception {
        log.info("Assigning role '{}' to Auth0 user '{}'", roleName, auth0UserId);

        String roleId = getRoleIdByName(roleName);
        if (roleId == null) {
            throw new IllegalArgumentException("Unknown role name: " + roleName + ". Must be EMPLOYEE, HR, or ADMIN");
        }

        ManagementAPI mgmt = getManagementAPI();
        mgmt.users().addRoles(auth0UserId, Collections.singletonList(roleId)).execute();

        log.info("Successfully assigned role '{}' (id: {}) to user '{}'", roleName, roleId, auth0UserId);
    }

    /**
     * Remove a role from a user by Auth0 user ID.
     *
     * @param auth0UserId The Auth0 user ID
     * @param roleName    Role name: "EMPLOYEE", "HR", or "ADMIN"
     * @throws Auth0Exception if the API call fails
     */
    public void removeRoleFromUser(String auth0UserId, String roleName) throws Auth0Exception {
        log.info("Removing role '{}' from Auth0 user '{}'", roleName, auth0UserId);

        String roleId = getRoleIdByName(roleName);
        if (roleId == null) {
            throw new IllegalArgumentException("Unknown role name: " + roleName + ". Must be EMPLOYEE, HR, or ADMIN");
        }

        ManagementAPI mgmt = getManagementAPI();
        mgmt.users().removeRoles(auth0UserId, Collections.singletonList(roleId)).execute();

        log.info("Successfully removed role '{}' (id: {}) from user '{}'", roleName, roleId, auth0UserId);
    }

    /**
     * Get all roles assigned to a user.
     *
     * @param auth0UserId The Auth0 user ID
     * @return List of role objects
     * @throws Auth0Exception if the API call fails
     */
    public List<Role> getUserRoles(String auth0UserId) throws Auth0Exception {
        ManagementAPI mgmt = getManagementAPI();
        return mgmt.users().listRoles(auth0UserId, null).execute().getBody().getItems();
    }

    /**
     * Map role name to Auth0 role ID from configuration.
     */
    private String getRoleIdByName(String roleName) {
        return switch (roleName.toUpperCase()) {
            case "EMPLOYEE" -> properties.getRoles().getEmployeeId();
            case "HR" -> properties.getRoles().getHrId();
            case "ADMIN" -> properties.getRoles().getAdminId();
            default -> null;
        };
    }
}
