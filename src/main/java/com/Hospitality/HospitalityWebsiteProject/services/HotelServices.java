package com.Hospitality.HospitalityWebsiteProject.services;

import com.Hospitality.HospitalityWebsiteProject.entity.HotelEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Service
public class HotelServices {
    private List<HotelEntity> hotels = new ArrayList<>();

    private Long currentId = 1L;

    public HotelEntity createHotel(HotelDTO dto){

        HotelEntity hotel = new HotelEntity();

        hotel.setId(currentId++);
        hotel.setName(dto.getName());
        hotel.setCity(dto.getCity());
        hotel.setState(dto.getState());
        hotel.setPricePerDay(dto.getPricePerDay());

        hotels.add(hotel);

        return hotel;
    }

    public List<HotelEntity> getHotels(){
        return hotels;
    }
}
