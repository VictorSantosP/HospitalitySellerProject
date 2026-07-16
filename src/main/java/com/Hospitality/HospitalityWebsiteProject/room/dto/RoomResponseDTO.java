package com.Hospitality.HospitalityWebsiteProject.room.dto;

import com.Hospitality.HospitalityWebsiteProject.room.enums.Avaliability;

public record RoomResponseDTO (
        Long id,
        Avaliability avaliable,
        Integer capacity,
        Integer number,
        Double price
){

}
