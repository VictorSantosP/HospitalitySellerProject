package com.Hospitality.HospitalityWebsiteProject.hotel.dto;

import jakarta.validation.constraints.*;

public record HotelRequestDTO (
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 200, message = "Nome maior que 200 ou menor que 0")
     String name,
    @NotBlank(message = "City name is mandatory")
    @Size(min = 3, max = 50, message = "Valor inválido, maior que 50 ou menor que 3")
     String city,
    @NotBlank(message = "State name is mandatory")
    @Size(min = 3, max = 50, message = "Valor inválido, maior que 50 ou menor que 3")
     String state
)
{ }
