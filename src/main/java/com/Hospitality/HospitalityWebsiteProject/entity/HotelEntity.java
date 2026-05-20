package com.Hospitality.HospitalityWebsiteProject.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class HotelEntity {

    private @Id
    @GeneratedValue Long id;
    private String name = "Pousada dos Pirineus";
    private String city = "Pirenopolis";
    private String state = "Goias";
    private Double pricePerDay = 250.00;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Hotel " + name
                + "Id:" + id
                + "Cidade e Estado"
                + city + "-" + state
                + String.format("%.2f",pricePerDay);
    }
}
