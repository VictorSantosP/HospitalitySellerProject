package com.Hospitality.HospitalityWebsiteProject.exception;


public class InvalidRequestException extends RuntimeException{
    public InvalidRequestException(){
        super ("Parâmetro(s) inválido(s): ");
    }
}
