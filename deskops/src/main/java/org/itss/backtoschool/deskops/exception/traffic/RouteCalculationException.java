package org.itss.backtoschool.deskops.exception.traffic;

public class RouteCalculationException extends RuntimeException {
    public RouteCalculationException() {
        super("Failed to calculate route");
    }

    public RouteCalculationException(String message) {
        super(message);
    }

    public RouteCalculationException(String message, Throwable cause) {
        super(message, cause);
    }
}
