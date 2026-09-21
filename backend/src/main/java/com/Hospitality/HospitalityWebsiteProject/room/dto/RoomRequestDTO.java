package com.Hospitality.HospitalityWebsiteProject.room.dto;

import com.Hospitality.HospitalityWebsiteProject.room.enums.Avaliability;
import jakarta.validation.constraints.*;

public record RoomRequestDTO(
    @NotNull(message = "Valor inválido, diferente de 'YES ' ou 'NO', ou dado enviado vazio!")
    Avaliability avaliable,
    @NotNull(message = "Dado enviado vazio!")
    @Positive
    @Min(value = 1, message = "Valor menor que 1!")
    @Max(value = 10, message = "Valor maior que 10!")
    Integer capacity,
    @NotNull(message = "Dado enviado vazio!")
    @Min(value = 1, message = "Valor menor que 1!")
    @Max(value = 1000, message = "Valor maior que 1000!")
    Integer number,
    @NotNull(message = "Dado enviado vazio!")
    @Positive(message = "Número negativo é inválido")
    @Max(value = 99999, message = "Valor maximo é 99999!")
    Double price,
    @NotNull(message = "Dado enviado vazio!")
    @Positive(message = "Número negativo é inválido")
    Long hotel_id
    )
{

}
