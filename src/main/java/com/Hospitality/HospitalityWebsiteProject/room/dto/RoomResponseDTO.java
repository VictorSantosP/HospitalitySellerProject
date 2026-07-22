package com.Hospitality.HospitalityWebsiteProject.room.dto;

import com.Hospitality.HospitalityWebsiteProject.reservation.dto.ReservationResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.room.enums.Avaliability;

import java.util.List;

public record RoomResponseDTO (
        Long id,
        Avaliability avaliable,
        Integer capacity,
        Integer number,
        Double price,
        String hotel_name,
        List<ReservationResponseDTO> reservations
){

}
