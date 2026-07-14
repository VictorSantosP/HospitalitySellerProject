package com.Hospitality.HospitalityWebsiteProject.exception;

public class HotelAlreadyExistsException extends RuntimeException {
    public HotelAlreadyExistsException() {
        super("Hotel já existe.");
    }
}
