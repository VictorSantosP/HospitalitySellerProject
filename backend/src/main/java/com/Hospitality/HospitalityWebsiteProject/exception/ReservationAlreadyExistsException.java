package com.Hospitality.HospitalityWebsiteProject.exception;

public class ReservationAlreadyExistsException extends RuntimeException {
    public ReservationAlreadyExistsException(String message) {
        super(message);
    }
}
