package com.Hospitality.HospitalityWebsiteProject.services;


import com.Hospitality.HospitalityWebsiteProject.DTO.HotelRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.DTO.HotelResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.entity.HotelEntity;
import com.Hospitality.HospitalityWebsiteProject.exception.DataIntegrityException;
import com.Hospitality.HospitalityWebsiteProject.exception.HotelAlreadyExistsException;
import com.Hospitality.HospitalityWebsiteProject.exception.HotelNotFoundException;
import com.Hospitality.HospitalityWebsiteProject.mapper.HotelMapper;
import com.Hospitality.HospitalityWebsiteProject.repository.HotelRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelServices {

    @Autowired
    private HotelMapper hotelMapper;
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public HotelResponseDTO createHotel(HotelRequestDTO hotelRequestDTO) {
        if (hotelRepository.existsByName(hotelRequestDTO.getName())) {
            throw new HotelAlreadyExistsException("Esse hotel já existe na base de dados.");
        }

        try {
            HotelEntity hotel = hotelMapper.toEntity(hotelRequestDTO);
            HotelEntity saved = hotelRepository.saveAndFlush(hotel);

            return hotelMapper.toResponseDTO(saved);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Erro de integridade de dados.");
        }
    }

    @Override
    public List<HotelResponseDTO> getAllHotels() {

        return hotelMapper.toResponseList(hotelRepository.findAll());
    }

    @Override
    public HotelResponseDTO getHotelById(Long id) {
        HotelEntity hotel = hotelRepository.findById(id).
                orElseThrow(() -> new HotelNotFoundException("Hotel não encontrado com o Id: " + id));

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
                throw new DataIntegrityViolationException("Erro de integridade de dados.");
            }
        } else {
            throw new HotelNotFoundException("Hotel não encontrado com o Id: " + id);
        }
    }

    @Override
    public HotelResponseDTO updateById(Long id, HotelRequestDTO hotelRequestDTO) {
            try {
                HotelEntity entity = hotelRepository.findById(id).orElseThrow(() ->
                        new HotelNotFoundException("Hotel não encontrado com o Id: " + id));
                if (!entity.getName().equals(hotelRequestDTO.getName())) {
                     entity.setName(hotelRequestDTO.getName());
                }
                if (!entity.getCity().equals(hotelRequestDTO.getCity())) {
                    entity.setCity(hotelRequestDTO.getCity());
                }
                if (!entity.getState().equals(hotelRequestDTO.getState())) {
                    entity.setState(hotelRequestDTO.getState());
                }
                if (!entity.getPricePerDay().equals(hotelRequestDTO.getPricePerDay())) {
                    entity.setPricePerDay(hotelRequestDTO.getPricePerDay());
                }
                @Valid HotelEntity saved = hotelRepository.saveAndFlush(entity);

                return hotelMapper.toResponseDTO(saved);
            } catch (DataIntegrityViolationException e) {
                throw new DataIntegrityException("Erro de integridade de dados.");
            }
    }
}
