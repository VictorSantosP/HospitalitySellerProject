package com.Hospitality.HospitalityWebsiteProject.DTO;

import jakarta.validation.constraints.*;

public class HotelRequestDTO {
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 200, message = "Nome maior que 200 ou menor que 0")
    private String name;
    @NotBlank(message = "City name is mandatory")
    @Size(min = 3, max = 50, message = "Valor inválido, maior que 50 ou menor que 3")
    private String city;
    @NotBlank(message = "State name is mandatory")
    @Size(min = 3, max = 50, message = "Valor inválido, maior que 50 ou menor que 3")
    private String state;
    @Positive
    @Min(value = 3, message = "Valor inválido, menor que 3")
    @Max(value = 99999, message =  "Valor inválido, maior que 99999")
    private Double pricePerDay;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}
