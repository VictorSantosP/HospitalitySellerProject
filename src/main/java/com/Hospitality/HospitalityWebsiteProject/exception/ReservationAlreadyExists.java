package com.Hospitality.HospitalityWebsiteProject.exception;

public class ReservationAlreadyExists extends RuntimeException {
    public ReservationAlreadyExists(String message) {
        super(message);
    }
}
