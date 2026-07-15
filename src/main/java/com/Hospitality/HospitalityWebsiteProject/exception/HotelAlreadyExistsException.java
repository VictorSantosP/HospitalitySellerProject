package com.Hospitality.HospitalityWebsiteProject.exception;

public class HotelAlreadyExistsException extends RuntimeException {
    public HotelAlreadyExistsException(String message) {
        super(message);
    }
}
