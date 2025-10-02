package org.itss.backtoschool.deskops.exception.seat;

public class SeatAlreadyReservedException extends RuntimeException{

    public SeatAlreadyReservedException() {
        super("This seat is already reserved for the selected date");
    }

    public SeatAlreadyReservedException(String message) {
        super(message);
    }
}
