package com.Hospitality.HospitalityWebsiteProject.exception;

public class HotelNotFoundException extends RuntimeException{
    public HotelNotFoundException(String message){
        super(message);
    }
}
