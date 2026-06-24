package com.Hospitality.HospitalityWebsiteProject.DTO;

public class HotelResponseDTO {

    Long id = null;
    String name = null;
    String city = null;
    String state = null;
    Double pricePerDay = null;

    public HotelResponseDTO(Long id, String name, String city, String state, Double pricePerDay) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.state = state;
        this.pricePerDay = pricePerDay;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }
}
