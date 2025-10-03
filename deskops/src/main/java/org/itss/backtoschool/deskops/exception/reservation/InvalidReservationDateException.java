package org.itss.backtoschool.deskops.exception.reservation;

public class InvalidReservationDateException extends RuntimeException {

    public InvalidReservationDateException() {
        super("Reservation date cannot be in the past");
    }

    public InvalidReservationDateException(String message) {
        super(message);
    }
}
