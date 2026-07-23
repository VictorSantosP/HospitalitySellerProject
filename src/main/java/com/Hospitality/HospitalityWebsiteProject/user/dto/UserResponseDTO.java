package com.Hospitality.HospitalityWebsiteProject.user.dto;

import com.Hospitality.HospitalityWebsiteProject.reservation.entity.ReservationEntity;
import com.Hospitality.HospitalityWebsiteProject.user.enums.UserRole;

import java.util.List;

public record UserResponseDTO (
        Long id,
        String name,
        String phone,
        UserRole role,
        List<ReservationEntity> reservations

) {
}
