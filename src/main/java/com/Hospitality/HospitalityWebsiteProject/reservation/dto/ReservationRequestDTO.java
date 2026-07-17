package com.Hospitality.HospitalityWebsiteProject.reservation.dto;

import com.Hospitality.HospitalityWebsiteProject.room.entity.RoomEntity;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record ReservationRequestDTO(
        @NotNull(message = "Valor não pode ser nulo")
        @FutureOrPresent
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate checkIn,
        @NotNull(message = "Valor não pode ser nulo")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate checkOut,
        @NotNull(message = "Valor não pode ser nulo")
        @Positive(message = "Valor não pode ser negativo")
        @Max(value = 999999, message = "Valor excedido. Limite = 999999")
        Long room_id
) {
}
