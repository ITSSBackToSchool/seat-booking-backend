package org.itss.backtoschool.deskops.exception.weather;

public class LocationNotConfiguredException extends RuntimeException {
    public LocationNotConfiguredException() {
        super("Location not configured for this building");
    }

    public LocationNotConfiguredException(String message) {
        super(message);
    }
}