package com.Hospitality.HospitalityWebsiteProject.hotel.mapper;

import com.Hospitality.HospitalityWebsiteProject.hotel.dto.HotelRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.hotel.dto.HotelResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.hotel.entity.HotelEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HotelMapper {

    List<HotelEntity> list = new ArrayList<>();


    public HotelEntity toEntity(HotelRequestDTO dto){

        HotelEntity hotel = new HotelEntity();

        hotel.setName(dto.name());
        hotel.setCity(dto.city());
        hotel.setState(dto.state());


        list.add(hotel);

        return hotel;
    }

    public HotelResponseDTO toResponseDTO(HotelEntity hotelEntity){

        return new HotelResponseDTO(hotelEntity.getId(),
                hotelEntity.getName(),
                hotelEntity.getCity(),
                hotelEntity.getState(),
                hotelEntity.getRooms());

    }

    public List<HotelResponseDTO> toResponseList(List<HotelEntity> hotelEntity){
        return hotelEntity.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());

    }
}
