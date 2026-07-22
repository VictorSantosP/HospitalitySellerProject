package com.Hospitality.HospitalityWebsiteProject.room.services;

import com.Hospitality.HospitalityWebsiteProject.exception.DataIntegrityException;
import com.Hospitality.HospitalityWebsiteProject.exception.HotelNotFoundException;
import com.Hospitality.HospitalityWebsiteProject.exception.RoomAlreadyExistsException;
import com.Hospitality.HospitalityWebsiteProject.exception.RoomNotFoundException;
import com.Hospitality.HospitalityWebsiteProject.hotel.entity.HotelEntity;
import com.Hospitality.HospitalityWebsiteProject.hotel.repository.HotelRepository;
import com.Hospitality.HospitalityWebsiteProject.room.dto.RoomRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.room.dto.RoomResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.room.entity.RoomEntity;
import com.Hospitality.HospitalityWebsiteProject.room.enums.Avaliability;
import com.Hospitality.HospitalityWebsiteProject.room.mapper.RoomMapper;
import com.Hospitality.HospitalityWebsiteProject.room.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomServices{

    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    @Transactional
    public RoomResponseDTO createRoom(RoomRequestDTO dto){
        HotelEntity hotel = hotelRepository.findById(dto.hotel_id())
                .orElseThrow(() -> new HotelNotFoundException(
                        "Hotel não encontrado com o ID: " + dto.hotel_id()
                ));
        if (roomRepository.existsByNumber(dto.number())) {
            throw new RoomAlreadyExistsException(
                    "Esse quarto já está registrado."
            );
        }
        try {

                RoomEntity room = roomMapper.toEntity(dto);
                room.setHotelEntity(hotel);
                RoomEntity saved = roomRepository.saveAndFlush(room);

                return roomMapper.toResponseDTO(saved);

            } catch (DataIntegrityViolationException e) {
                throw new DataIntegrityException(
                        "Erro de integridade de dados."
                );

        }
    }

    @Override
    public Page<RoomResponseDTO> findAll(Pageable pageable){
        Page<RoomEntity> rooms = roomRepository.findAll(pageable);

        return rooms.map(roomMapper::toResponseDTO);
    }

    @Override
    public RoomResponseDTO findById(Long id){
        RoomEntity room = roomRepository.findById(id).
                orElseThrow(() -> new RoomNotFoundException(
                        "Quarto não encontrado com o Id: " + id
                ));
        return roomMapper.toResponseDTO(room);
    }

    @Override
    @Transactional
    public void deleteById(Long id){
        if(roomRepository.existsById(id)){
            try{
                roomRepository.deleteById(id);

            }catch (DataIntegrityViolationException e){
                throw new DataIntegrityException(
                        "Erro de integridade de dados."
                );
            }
        }else{
            throw new RoomNotFoundException(
                    "Quarto não encontrado com o Id: " + id
            );
        }
    }

    @Override
    @Transactional
    public RoomResponseDTO updateById(Long id, RoomRequestDTO dto){
        try{
            RoomEntity room = roomRepository.findById(id).orElseThrow(
                    () -> new RoomNotFoundException(
                            "Quarto não encontrado com Id: " + id
                    ));
            if(!room.getAvaliability().equals(dto.avaliable())){
                room.setAvaliability(dto.avaliable());
            }
            if(!room.getCapacity().equals(dto.capacity())){
                room.setCapacity(dto.capacity());
            }
            if(!room.getNumber().equals(dto.number())){
                room.setNumber(dto.number());
            }
            if(!room.getPrice().equals(dto.price())){
                room.setPrice(dto.price());
            }

            RoomEntity saved = roomRepository.saveAndFlush(room);

            return roomMapper.toResponseDTO(saved);

        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityException(
                    "Erro de integridade de dados."
            );
        }
    }

    @Override
    public List<RoomResponseDTO> findByAvaliability(Avaliability avaliability){
        if(!roomRepository.existsByAvaliability(avaliability)){
            throw new RoomNotFoundException(
                    "Não há hotéis disponíveis."
            );
        }
        return roomMapper.toResponseList(
                roomRepository.findAllByAvaliability(avaliability));
    }
    @Override
    public List<RoomResponseDTO> findByAvaliabilityAndHotelId(Avaliability avaliability, Long hotelId){
        if(!roomRepository.existsByAvaliabilityAndHotelEntity_Id(avaliability, hotelId)){
            throw new RoomNotFoundException(
                    "Não há hotéis disponíveis."
            );
        }
        return roomMapper.toResponseList(
                roomRepository.findAllByAvaliabilityAndHotelEntity_Id(avaliability, hotelId));
    }
    @Override
    public List<RoomResponseDTO> findByNumber(Integer number){
        if(!roomRepository.existsByNumber(number)){
            throw new RoomNotFoundException(
                    "Não há hotéis com o número: " + number
            );
        }
        return roomMapper.toResponseList(
                roomRepository.findAllByNumber(number));
    }
    @Override
    public List<RoomResponseDTO> findByCapacity(Integer capacity){
        if(!roomRepository.existsByCapacity(capacity)){
            throw new RoomNotFoundException(
                    "Não há hotéis com a capacidade: " + capacity
            );
        }
        return roomMapper.toResponseList(
                roomRepository.findAllByCapacity(capacity));
    }
    @Override
    public List<RoomResponseDTO> findByCapacityLessThan(Integer capacity){
        if(!roomRepository.existsByCapacityLessThan(capacity)){
            throw new RoomNotFoundException(
                    "Não há hotéis com a capacidade menor que: " + capacity
            );
        }
        return roomMapper.toResponseList(
                roomRepository.findAllByCapacityLessThan(capacity));
    }
    @Override
    public List<RoomResponseDTO> findByCapacityGreaterThan(Integer capacity){
        if(!roomRepository.existsByCapacityGreaterThan(capacity)){
            throw new RoomNotFoundException(
                    "Não há hotéis com a capacidade maior que: " + capacity
            );
        }
        return roomMapper.toResponseList(
                roomRepository.findAllByCapacityGreaterThan(capacity));
    }
    @Override
    public List<RoomResponseDTO> findByCapacityBetween(Integer min, Integer max){
        if(!roomRepository.existsByCapacityBetween(min, max)){
            throw new RoomNotFoundException(
                    "Não há hotéis com a capacidade entre: " + min
                            + " e " + max
            );
        }
        return roomMapper.toResponseList(
                roomRepository.findAllByCapacityBetween(min, max));
    }
    @Override
    public List<RoomResponseDTO> findByPriceLessThan(Double price){
        if(!roomRepository.existsByPriceLessThan(price)){
            throw new RoomNotFoundException(
                    "Não há hotéis disponíveis com o preço menor que: " + price
            );
        }
        return roomMapper.toResponseList(
                roomRepository.findAllByPriceLessThan(price));
    }

    @Override
    public List<RoomResponseDTO> findByPriceGreaterThan(Double price){
        if(!roomRepository.existsByPriceGreaterThan(price)){
            throw new RoomNotFoundException(
                    "Não há hotéis disponíveis com o preço maior que: " + price
            );
        }
        return roomMapper.toResponseList(
                roomRepository.findAllByPriceGreaterThan(price));
    }
    @Override
    public List<RoomResponseDTO> findByPriceBetween(Double min, Double max){
        if(!roomRepository.existsByPriceBetween(min, max)){
            throw new RoomNotFoundException(
                    "Não há hotéis disponíveis com o preço entre: " + min
                    + " e " + max
            );
        }
        return roomMapper.toResponseList(
                roomRepository.findAllByPriceBetween(min, max));
    }
}
