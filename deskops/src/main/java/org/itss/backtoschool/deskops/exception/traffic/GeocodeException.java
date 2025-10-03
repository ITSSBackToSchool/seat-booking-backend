package org.itss.backtoschool.deskops.exception.traffic;

public class GeocodeException extends RuntimeException {
    public GeocodeException() {
        super("Failed to geocode the provided address");
    }

    public GeocodeException(String message) {
        super(message);
    }

    public GeocodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
