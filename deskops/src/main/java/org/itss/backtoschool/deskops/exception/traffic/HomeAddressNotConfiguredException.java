package org.itss.backtoschool.deskops.exception.traffic;

public class HomeAddressNotConfiguredException extends RuntimeException {
    public HomeAddressNotConfiguredException() {
        super("Home address not configured for this user");
    }

    public HomeAddressNotConfiguredException(String message) {
        super(message);
    }
}
