package com.Hospitality.HospitalityWebsiteProject.room.dto;

import com.Hospitality.HospitalityWebsiteProject.room.enums.Avaliability;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record RoomRequestDTO(
    @NotBlank(message = "Valor inválido, diferente de 'YES ' ou 'NO', ou dado enviado vazio!")
    Avaliability avaliable,
    @NotBlank(message = "Dado enviado vazio!")
    @Positive
    @Min(value = 1, message = "Valor menor que 1!")
    @Max(value = 10, message = "Valor maior que 10!")
    Integer capacity,
    @NotBlank(message = "Dado enviado vazio!")
    @Min(value = 1, message = "Valor menor que 1!")
    @Max(value = 1000, message = "Valor maior que 1000!")
    Integer number,
    @NotBlank(message = "Dado enviado vazio!")
    @Positive(message = "Número negativo é inválido")
    @Max(value = 99999, message = "Valor maximo é 99999!")
    Double price,
    @NotBlank(message = "Dado enviado vazio!")
    @Positive(message = "Número negativo é inválido")
    Long hotel_id
    )
{

}
