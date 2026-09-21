
package com.Hospitality.HospitalityWebsiteProject.room.services;

import com.Hospitality.HospitalityWebsiteProject.hotel.dto.HotelRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.hotel.dto.HotelResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.room.dto.RoomRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.room.dto.RoomResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.room.enums.Avaliability;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoomServices {
    RoomResponseDTO createRoom (RoomRequestDTO requestDTO);
    Page<RoomResponseDTO> findAll(Pageable pageable);
    RoomResponseDTO findById(Long id);
    void deleteById(Long id);
    RoomResponseDTO updateById(Long id, RoomRequestDTO requestDTO);
    List<RoomResponseDTO> findByAvaliability(Avaliability avaliability);
    List<RoomResponseDTO> findByAvaliabilityAndHotelId(Avaliability avaliability, Long hotelId);
    List<RoomResponseDTO> findByNumber(Integer number);
    List<RoomResponseDTO> findByCapacity(Integer capacity);
    List<RoomResponseDTO> findByCapacityLessThan(Integer capacity);
    List<RoomResponseDTO> findByCapacityGreaterThan(Integer capacity);
    List<RoomResponseDTO> findByCapacityBetween(Integer min, Integer max);
    List<RoomResponseDTO> findByPriceLessThan(Double price);
    List<RoomResponseDTO> findByPriceGreaterThan(Double price);
    List<RoomResponseDTO> findByPriceBetween(Double min, Double max);


}
