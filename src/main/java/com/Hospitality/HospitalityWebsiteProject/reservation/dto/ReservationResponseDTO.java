package com.Hospitality.HospitalityWebsiteProject.reservation.dto;

import java.time.LocalDate;

public record ReservationResponseDTO(
        Long id,
        LocalDate checkIn,
        LocalDate checkOut,
        String room_id,
        String hotel_name
) {

}
