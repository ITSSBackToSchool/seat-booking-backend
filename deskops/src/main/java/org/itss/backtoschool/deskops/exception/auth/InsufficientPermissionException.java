package org.itss.backtoschool.deskops.exception.auth;

import org.itss.backtoschool.deskops.entities.Permission;

public class InsufficientPermissionException extends RuntimeException {

    public InsufficientPermissionException(Permission permission) {
        super("Insufficient permissions: " + permission.name() + " required");
    }

    public InsufficientPermissionException(String message) {
        super(message);
    }
}
