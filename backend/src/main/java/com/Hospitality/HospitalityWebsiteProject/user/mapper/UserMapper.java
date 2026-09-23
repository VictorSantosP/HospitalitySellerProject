package com.Hospitality.HospitalityWebsiteProject.user.mapper;

import com.Hospitality.HospitalityWebsiteProject.reservation.dto.ReservationResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.reservation.mapper.ReservationMapper;
import com.Hospitality.HospitalityWebsiteProject.room.dto.RoomResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.room.entity.RoomEntity;
import com.Hospitality.HospitalityWebsiteProject.security.dto.RegisterRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.security.dto.RegisterResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.user.dto.UserRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.user.dto.UserResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ReservationMapper reservationMapper;

    public UserEntity toEntity(RegisterRequestDTO dto){
        UserEntity user = new UserEntity();

        user.setEmail(dto.email());
        user.setName(dto.name());
        user.setPassword(dto.password());

        return user;
    }

    public UserEntity toEntityFromRegister(RegisterRequestDTO dto) {
        UserEntity user = new UserEntity();

        user.setEmail(dto.email());
        user.setName(dto.name());

        user.setPassword(dto.password());

        return user;
    }

    public UserResponseDTO toResponseDTO(UserEntity userEntity){
        List<ReservationResponseDTO> reservations = userEntity
                .getReservations()
                .stream()
                .map(reservationMapper::toResponseDTO).toList();
        return new UserResponseDTO(userEntity.getId(),
                userEntity.getName(),
                reservations);
    }



}
