package com.Hospitality.HospitalityWebsiteProject.security.dto;

public record RegisterRequestDTO(
        String name,
        String email,
        String password
) {
}
