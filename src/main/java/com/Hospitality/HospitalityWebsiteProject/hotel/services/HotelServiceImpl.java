package com.Hospitality.HospitalityWebsiteProject.hotel.services;


import com.Hospitality.HospitalityWebsiteProject.hotel.dto.HotelRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.hotel.dto.HotelResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.hotel.entity.HotelEntity;
import com.Hospitality.HospitalityWebsiteProject.exception.DataIntegrityException;
import com.Hospitality.HospitalityWebsiteProject.exception.HotelAlreadyExistsException;
import com.Hospitality.HospitalityWebsiteProject.exception.HotelNotFoundException;
import com.Hospitality.HospitalityWebsiteProject.hotel.mapper.HotelMapper;
import com.Hospitality.HospitalityWebsiteProject.hotel.repository.HotelRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelServices {

    @Autowired
    private HotelMapper hotelMapper;
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public HotelResponseDTO createHotel(HotelRequestDTO hotelRequestDTO) {
        if (hotelRepository.existsByName(hotelRequestDTO.name())) {
            throw new HotelAlreadyExistsException(
                    "Esse hotel já existe na base de dados.");
        }

        try {
            HotelEntity hotel = hotelMapper.toEntity(hotelRequestDTO);
            HotelEntity saved = hotelRepository.saveAndFlush(hotel);

            return hotelMapper.toResponseDTO(saved);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(
                    "Erro de integridade de dados.");
        }
    }

    @Override
    public Page<HotelResponseDTO> getAllHotels(Pageable pageable) {
        Page<HotelEntity> hotels = hotelRepository.findAll(pageable);

        return hotels.map(hotelMapper::toResponseDTO);
    }

    @Override
    public HotelResponseDTO getHotelById(Long id) {
        HotelEntity hotel = hotelRepository.findById(id).
                orElseThrow(() -> new HotelNotFoundException(
                        "Hotel não encontrado com o Id: " + id));

        return hotelMapper.toResponseDTO(hotel);
    }

    @Override
    public void deleteById(Long id) {
        if (hotelRepository.existsById(id)) {
            try {
                if (hotelRepository.existsById(id)) {
                    hotelRepository.deleteById(id);
                }
            } catch (DataIntegrityViolationException e) {
                throw new DataIntegrityViolationException(
                        "Erro de integridade de dados.");
            }
        } else {
            throw new HotelNotFoundException(
                    "Hotel não encontrado com o Id: " + id);
        }
    }

    @Override
    public HotelResponseDTO updateById(Long id, HotelRequestDTO hotelRequestDTO) {
            try {
                HotelEntity entity = hotelRepository.findById(id).orElseThrow(() ->
                        new HotelNotFoundException(
                                "Hotel não encontrado com o Id: " + id));
                if (!entity.getName().equals(hotelRequestDTO.name())) {
                     entity.setName(hotelRequestDTO.name());
                }
                if (!entity.getCity().equals(hotelRequestDTO.city())) {
                    entity.setCity(hotelRequestDTO.city());
                }
                if (!entity.getState().equals(hotelRequestDTO.state())) {
                    entity.setState(hotelRequestDTO.state());
                }

                @Valid HotelEntity saved = hotelRepository.saveAndFlush(entity);

                return hotelMapper.toResponseDTO(saved);
            } catch (DataIntegrityViolationException e) {
                throw new DataIntegrityException(
                        "Erro de integridade de dados.");
            }
    }

    @Override
    public List<HotelResponseDTO> findByCity(String city){
        if(!hotelRepository.existsByCityIgnoreCase(city)){
            throw new HotelNotFoundException(
                    "Não há hotel cadastrado na cidade: " + city);
        }
        return hotelMapper.toResponseList(hotelRepository.findAllByCityIgnoreCase(city));
    }

    @Override
    public List<HotelResponseDTO> findByState(String state){
        if(!hotelRepository.existsByStateIgnoreCase(state)){
            throw new HotelNotFoundException(
                    "Não há hotel cadastrado no estado: " + state);
        }
        return hotelMapper.toResponseList(hotelRepository.findAllByStateIgnoreCase(state));
    }

    @Override
    public List<HotelResponseDTO> findByName(String name){
        if(!hotelRepository.existsByNameIgnoreCase(name)){
            throw new HotelNotFoundException(
                    "Não há hotel cadastrado com o nome: " + name);
        }
        return hotelMapper.toResponseList(hotelRepository.findAllByNameIgnoreCase(name));
    }

    @Override
    public List<HotelResponseDTO> findByNameContaining(String name){
        if(!hotelRepository.existsByNameContainingIgnoreCase(name)){
            throw new HotelNotFoundException(
                    "Não há hotel cadastrado com a(s) palavra(s):  " + name
            );
        }
        return  hotelMapper.toResponseList(hotelRepository.findAllByNameContainingIgnoreCase(name));
    }


}
