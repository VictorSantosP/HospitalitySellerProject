package com.Hospitality.HospitalityWebsiteProject.user.dto;

import com.Hospitality.HospitalityWebsiteProject.user.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserRequestDTO (
      @NotBlank
      String name,
      @Email(
              regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
              message = "E-mail inválido."
      )
      String email,
      @NotBlank
      String password,
      @NotBlank(message = "Telefone é obrigatório.")
      @Pattern(
              regexp = "^\\d{10,11}$",
              message = "Telefone deve conter 10 ou 11 dígitos."
      )
      String phone,
      @NotNull(message = "Valor inválido, diferente de 'ADMIN ' ou 'USER', ou dado enviado vazio!")
      UserRole role
) {
}
