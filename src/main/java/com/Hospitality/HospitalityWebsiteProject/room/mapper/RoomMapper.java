package com.Hospitality.HospitalityWebsiteProject.room.mapper;

import com.Hospitality.HospitalityWebsiteProject.exception.HotelNotFoundException;
import com.Hospitality.HospitalityWebsiteProject.hotel.entity.HotelEntity;
import com.Hospitality.HospitalityWebsiteProject.hotel.repository.HotelRepository;
import com.Hospitality.HospitalityWebsiteProject.room.dto.RoomRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.room.dto.RoomResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.room.entity.RoomEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.stream.Collectors;

@Component
public class RoomMapper {
    private HotelRepository hotelRepo;

    public RoomEntity toEntity(RoomRequestDTO dto){
        RoomEntity room = new RoomEntity();

        HotelEntity found = hotelRepo.findById(dto.hotel_id()).
                orElseThrow(() -> new HotelNotFoundException("" +
                        "Hotel não encontrado com o ID: " + dto.hotel_id()));

        room.setAvaliable(dto.avaliable());
        room.setCapacity(dto.capacity());
        room.setHotelEntity(found);
        room.setPrice(dto.price());
        room.setNumber(dto.number());

        return room;
    }

    public RoomResponseDTO toResponseDTO(RoomEntity roomEntity){
        return new RoomResponseDTO(roomEntity.getId(),
                roomEntity.getAvaliable(),
                roomEntity.getCapacity(),
                roomEntity.getNumber(),
                roomEntity.getPrice());
    }

    public List<RoomResponseDTO> toResponseList(List<RoomEntity> roomEntities){
        return roomEntities.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

}
