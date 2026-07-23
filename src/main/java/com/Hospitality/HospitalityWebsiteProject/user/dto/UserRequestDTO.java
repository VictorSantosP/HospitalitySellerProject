package com.Hospitality.HospitalityWebsiteProject.user.dto;

import com.Hospitality.HospitalityWebsiteProject.user.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO (
      @NotBlank
      String name,
      @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
      String email,
      @NotBlank
      String password,
      @NotBlank
      String phone,
      @NotNull(message = "Valor inválido, diferente de 'ADMIN ' ou 'USER', ou dado enviado vazio!")
      UserRole role
) {
}
