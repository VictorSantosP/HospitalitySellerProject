package com.Hospitality.HospitalityWebsiteProject.services;


import com.Hospitality.HospitalityWebsiteProject.DTO.HotelRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.DTO.HotelResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.entity.HotelEntity;
import com.Hospitality.HospitalityWebsiteProject.mapper.HotelMapper;
import com.Hospitality.HospitalityWebsiteProject.repository.HotelRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelServices{

    @Autowired
    private HotelMapper hotelMapper;
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public HotelResponseDTO createHotel(HotelRequestDTO hotelRequestDTO){
        HotelEntity hotel = hotelMapper.toEntity(hotelRequestDTO);
        HotelEntity saved = hotelRepository.saveAndFlush(hotel);


        return hotelMapper.toResponseDTO(saved);
    }

    @Override
    public List<HotelResponseDTO> getAllHotels(){

        return hotelMapper.toResponseList(hotelRepository.findAll());
    }

    @Override
    public HotelResponseDTO getHotelById(Long id){
        return hotelMapper.toResponseDTO(
                hotelRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException(
                                "Hotel nao encontrado com o id: " + id)));
    }

    @Override
    public void deleteById(Long id){
        try{
            if(hotelRepository.existsById(id)){
                hotelRepository.deleteById(id);
            }
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(e.getMessage());
        }
    }

    @Override
    public HotelResponseDTO updateById(Long id, HotelRequestDTO hotelRequestDTO){
        try{
            HotelEntity entity = hotelRepository.getReferenceById(id);
            if(!entity.getName().equals(hotelRequestDTO.getName())){
                entity.setName(hotelRequestDTO.getName());
            }
            if(!entity.getCity().equals(hotelRequestDTO.getCity())){
                entity.setCity(hotelRequestDTO.getCity());
            }
            if(!entity.getState().equals(hotelRequestDTO.getState())){
                entity.setState(hotelRequestDTO.getState());
            }
            if(!entity.getPricePerDay().equals(hotelRequestDTO.getPricePerDay())){
                entity.setPricePerDay(hotelRequestDTO.getPricePerDay());
            }
            HotelEntity saved = hotelRepository.saveAndFlush(entity);

            return hotelMapper.toResponseDTO(saved);
        }catch(EntityNotFoundException e){
            throw new EntityNotFoundException(e.getMessage());
        }
    }

}
