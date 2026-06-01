package com.Hospitality.HospitalityWebsiteProject.services;


import com.Hospitality.HospitalityWebsiteProject.DTO.HotelRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.DTO.HotelResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.entity.HotelEntity;
import com.Hospitality.HospitalityWebsiteProject.mapper.HotelMapper;
import com.Hospitality.HospitalityWebsiteProject.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelServices{

    @Autowired
    private HotelMapper hotelMapper;
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public HotelResponseDTO createHotel(HotelRequestDTO hotelRequestDTO){
        HotelEntity hotel = hotelMapper.toEntity(hotelRequestDTO);
        HotelEntity saved = hotelRepository.saveAndFlush(hotel);


        return hotelMapper.toResponseDTO(saved);
    }

    @Override
    public List<HotelResponseDTO> getAllHotels(){
        return hotelMapper.toResponseList(hotelRepository.findAll());
    }
}
