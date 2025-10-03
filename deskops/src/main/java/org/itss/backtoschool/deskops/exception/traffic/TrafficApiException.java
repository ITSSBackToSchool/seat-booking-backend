package org.itss.backtoschool.deskops.exception.traffic;

public class TrafficApiException extends RuntimeException {
    public TrafficApiException() {
        super("Failed to fetch traffic data from external API");
    }

    public TrafficApiException(String message) {
        super(message);
    }

    public TrafficApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
