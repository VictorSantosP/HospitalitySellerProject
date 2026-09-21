package com.Hospitality.HospitalityWebsiteProject.reservation.mapper;

import com.Hospitality.HospitalityWebsiteProject.exception.RoomNotFoundException;
import com.Hospitality.HospitalityWebsiteProject.reservation.dto.ReservationRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.reservation.dto.ReservationResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.reservation.entity.ReservationEntity;
import com.Hospitality.HospitalityWebsiteProject.room.entity.RoomEntity;
import com.Hospitality.HospitalityWebsiteProject.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationMapper {
    private final RoomRepository roomRepo;

    public ReservationEntity toEntity(ReservationRequestDTO dto){
        ReservationEntity reservation = new ReservationEntity();
        reservation.setCheckIn(dto.checkIn());
        reservation.setCheckOut(dto.checkOut());

        return reservation;
    }

    public ReservationResponseDTO toResponseDTO(ReservationEntity reservation){
        return new ReservationResponseDTO(reservation.getId(),
                reservation.getCheckIn(),
                reservation.getCheckOut(),
                reservation.getRoom().getId(),
                reservation.getRoom().getHotelEntity().getName());
    }

}
