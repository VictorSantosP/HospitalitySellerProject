package com.Hospitality.HospitalityWebsiteProject.hotel.dto;

import com.Hospitality.HospitalityWebsiteProject.room.dto.RoomResponseDTO;

import java.util.List;

public record HotelResponseDTO(
        Long id,
        String name,
        String city,
        String state,
        List<RoomResponseDTO> rooms
) {
}
