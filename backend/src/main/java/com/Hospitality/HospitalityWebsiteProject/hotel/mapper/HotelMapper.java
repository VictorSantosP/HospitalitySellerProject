package com.Hospitality.HospitalityWebsiteProject.hotel.mapper;

import com.Hospitality.HospitalityWebsiteProject.hotel.dto.HotelRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.hotel.dto.HotelResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.hotel.entity.HotelEntity;
import com.Hospitality.HospitalityWebsiteProject.room.dto.RoomResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.room.mapper.RoomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HotelMapper {

    private final RoomMapper roomMapper;


    public HotelEntity toEntity(HotelRequestDTO dto){

        HotelEntity hotel = new HotelEntity();

        hotel.setName(dto.name());
        hotel.setCity(dto.city());
        hotel.setState(dto.state());



        return hotel;
    }

    public HotelResponseDTO toResponseDTO(HotelEntity hotelEntity){

        List<RoomResponseDTO> rooms = hotelEntity.getRooms()
                .stream().map(roomMapper::toResponseDTO).toList();

        return new HotelResponseDTO(hotelEntity.getId(),
                hotelEntity.getName(),
                hotelEntity.getCity(),
                hotelEntity.getState(),
                rooms);

    }

    public List<HotelResponseDTO> toResponseList(List<HotelEntity> hotelEntity){
        return hotelEntity.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());

    }
}
