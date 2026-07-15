package com.Hospitality.HospitalityWebsiteProject.services;

import com.Hospitality.HospitalityWebsiteProject.DTO.HotelRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.DTO.HotelResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.entity.HotelEntity;
import com.Hospitality.HospitalityWebsiteProject.repository.HotelRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.ArrayList;
import java.util.List;


public interface HotelServices {

    HotelResponseDTO createHotel(HotelRequestDTO hotelRequestDTO);
    Page<HotelResponseDTO> getAllHotels(Pageable pageable);
    HotelResponseDTO getHotelById(Long id);
    void deleteById(Long id);
    HotelResponseDTO updateById(Long id, HotelRequestDTO hotelRequestDTO);
    List<HotelResponseDTO> findByCity(String city);
    List<HotelResponseDTO> findByState(String state);
    List<HotelResponseDTO> findByName(String name);
    List<HotelResponseDTO> findByNameContaining(String name);
    List<HotelResponseDTO> findByPricePerDayLessThan(Double price);
    List<HotelResponseDTO> findByPricePerDayGreaterThan(Double price);
    List<HotelResponseDTO> findByPricePerDayBetween(Double min, Double max);

}
