package com.Hospitality.HospitalityWebsiteProject.DTO;

import com.Hospitality.HospitalityWebsiteProject.entity.HotelEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class HotelResponseDTO {

    Long id = null;
    String name = null;
    String city = null;
    String state = null;
    Double setPricepPerDay = null;

    public HotelResponseDTO(Long id, String name, String city, String state, Double setPricepPerDay) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.state = state;
        this.setPricepPerDay = setPricepPerDay;
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

    public Double getSetPricepPerDay() {
        return setPricepPerDay;
    }
}
