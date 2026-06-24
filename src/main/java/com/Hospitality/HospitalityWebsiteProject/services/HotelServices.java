package com.Hospitality.HospitalityWebsiteProject.services;

import com.Hospitality.HospitalityWebsiteProject.DTO.HotelRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.DTO.HotelResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.entity.HotelEntity;
import com.Hospitality.HospitalityWebsiteProject.repository.HotelRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public interface HotelServices {

    HotelResponseDTO createHotel(HotelRequestDTO hotelRequestDTO);
    List<HotelResponseDTO> getAllHotels();
    HotelResponseDTO getHotelById(Long id);
    void deleteById(Long id);
    HotelResponseDTO updateById(Long id, HotelRequestDTO hotelRequestDTO);
}
