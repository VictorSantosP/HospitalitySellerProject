package com.Hospitality.HospitalityWebsiteProject.room.mapper;

import com.Hospitality.HospitalityWebsiteProject.exception.HotelNotFoundException;
import com.Hospitality.HospitalityWebsiteProject.hotel.entity.HotelEntity;
import com.Hospitality.HospitalityWebsiteProject.hotel.repository.HotelRepository;
import com.Hospitality.HospitalityWebsiteProject.room.dto.RoomRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.room.dto.RoomResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.room.entity.RoomEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoomMapper {
    private final HotelRepository hotelRepo;

    public RoomEntity toEntity(RoomRequestDTO dto){
        RoomEntity room = new RoomEntity();

        room.setAvaliability(dto.avaliable());
        room.setCapacity(dto.capacity());
        room.setPrice(dto.price());
        room.setNumber(dto.number());

        return room;
    }

    public RoomResponseDTO toResponseDTO(RoomEntity roomEntity){
        return new RoomResponseDTO(roomEntity.getId(),
                roomEntity.getAvaliability(),
                roomEntity.getCapacity(),
                roomEntity.getNumber(),
                roomEntity.getPrice(),
                roomEntity.getHotelEntity().getName()
        );
    }

    public List<RoomResponseDTO> toResponseList(List<RoomEntity> roomEntities){
        return roomEntities.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

}
