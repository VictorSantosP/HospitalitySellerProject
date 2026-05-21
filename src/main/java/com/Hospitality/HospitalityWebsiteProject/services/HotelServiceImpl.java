package com.Hospitality.HospitalityWebsiteProject.services;


import com.Hospitality.HospitalityWebsiteProject.DTO.HotelRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.DTO.HotelResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.entity.HotelEntity;
import com.Hospitality.HospitalityWebsiteProject.mapper.HotelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelServices{

    List<HotelEntity> hotelEntityList = new ArrayList<>();

    @Autowired
    HotelMapper hotelMapper = new HotelMapper();

    @Override
    public HotelResponseDTO createHotel(HotelRequestDTO hotelRequestDTO){
        HotelEntity hotel = hotelMapper.toEntity(hotelRequestDTO);

        hotelEntityList.add(hotel);

        return hotelMapper.toResponseDTO(hotel);
    }

    @Override
    public List<HotelResponseDTO> getAllHotels(){
        return hotelMapper.toResponseList(hotelEntityList);
    }
}
