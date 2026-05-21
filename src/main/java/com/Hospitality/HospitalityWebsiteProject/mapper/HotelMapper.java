package com.Hospitality.HospitalityWebsiteProject.mapper;

import com.Hospitality.HospitalityWebsiteProject.DTO.HotelRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.DTO.HotelResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.entity.HotelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class HotelMapper {

    List<HotelEntity> list = new ArrayList<>();

    private Long currentId = 1L;

    public HotelEntity toEntity(HotelRequestDTO dto){

        HotelEntity hotel = new HotelEntity();

        hotel.setId(currentId++);
        hotel.setName(dto.getName());
        hotel.setCity(dto.getCity());
        hotel.setState(dto.getState());
        hotel.setPricePerDay(dto.getPricePerDay());

        list.add(hotel);

        return hotel;
    }

    public HotelResponseDTO toResponseDTO(HotelEntity hotelEntity){

        return new HotelResponseDTO(hotelEntity.getId(),
                hotelEntity.getName(),
                hotelEntity.getCity(),
                hotelEntity.getState(),
                hotelEntity.getPricePerDay());
    }

    public List<HotelResponseDTO> toResponseList(List<HotelEntity> hotelEntity){
        return hotelEntity.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());

    }
}
