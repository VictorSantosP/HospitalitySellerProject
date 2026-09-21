package com.Hospitality.HospitalityWebsiteProject.hotel.services;

import com.Hospitality.HospitalityWebsiteProject.exception.DataIntegrityException;
import com.Hospitality.HospitalityWebsiteProject.exception.HotelAlreadyExistsException;
import com.Hospitality.HospitalityWebsiteProject.exception.HotelNotFoundException;
import com.Hospitality.HospitalityWebsiteProject.hotel.dto.HotelRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.hotel.dto.HotelResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.hotel.entity.HotelEntity;
import com.Hospitality.HospitalityWebsiteProject.hotel.mapper.HotelMapper;
import com.Hospitality.HospitalityWebsiteProject.hotel.repository.HotelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelServices {

    private final HotelMapper hotelMapper;
    private final HotelRepository hotelRepository;


    @Override
    @Transactional
    public HotelResponseDTO createHotel(
            HotelRequestDTO hotelRequestDTO
    ) {

        if (hotelRepository.existsByNameIgnoreCase(
                hotelRequestDTO.name()
        )) {
            throw new HotelAlreadyExistsException(
                    "Esse hotel já existe na base de dados."
            );
        }

        try {

            HotelEntity hotel =
                    hotelMapper.toEntity(hotelRequestDTO);


            if (hotel.getRooms() != null) {

                hotel.getRooms().forEach(
                        room -> room.setHotelEntity(hotel)
                );
            }

            HotelEntity saved =
                    hotelRepository.saveAndFlush(hotel);

            return hotelMapper.toResponseDTO(saved);

        } catch (DataIntegrityViolationException e) {

            throw new DataIntegrityException(
                    "Erro de integridade de dados."
            );
        }
    }


    @Override
    public Page<HotelResponseDTO> getAllHotels(
            Pageable pageable
    ) {

        Page<HotelEntity> hotels =
                hotelRepository.findAll(pageable);

        return hotels.map(
                hotelMapper::toResponseDTO
        );
    }


    @Override
    public HotelResponseDTO getHotelById(Long id) {

        HotelEntity hotel =
                hotelRepository.findById(id)
                        .orElseThrow(
                                () -> new HotelNotFoundException(
                                        "Hotel não encontrado com o Id: "
                                                + id
                                )
                        );

        return hotelMapper.toResponseDTO(hotel);
    }


    @Override
    @Transactional
    public void deleteById(Long id) {

        HotelEntity hotel =
                hotelRepository.findById(id)
                        .orElseThrow(
                                () -> new HotelNotFoundException(
                                        "Hotel não encontrado com o Id: "
                                                + id
                                )
                        );

        try {

            hotelRepository.delete(hotel);

        } catch (DataIntegrityViolationException e) {

            throw new DataIntegrityException(
                    "Erro de integridade de dados."
            );
        }
    }


    @Override
    @Transactional
    public HotelResponseDTO updateById(
            Long id,
            HotelRequestDTO hotelRequestDTO
    ) {

        try {

            HotelEntity entity =
                    hotelRepository.findById(id)
                            .orElseThrow(
                                    () -> new HotelNotFoundException(
                                            "Hotel não encontrado com o Id: "
                                                    + id
                                    )
                            );

            if (!entity.getName().equals(
                    hotelRequestDTO.name()
            )) {
                entity.setName(
                        hotelRequestDTO.name()
                );
            }

            if (!entity.getCity().equals(
                    hotelRequestDTO.city()
            )) {
                entity.setCity(
                        hotelRequestDTO.city()
                );
            }

            if (!entity.getState().equals(
                    hotelRequestDTO.state()
            )) {
                entity.setState(
                        hotelRequestDTO.state()
                );
            }

            HotelEntity saved =
                    hotelRepository.saveAndFlush(entity);

            return hotelMapper.toResponseDTO(saved);

        } catch (DataIntegrityViolationException e) {

            throw new DataIntegrityException(
                    "Erro de integridade de dados."
            );
        }
    }


    @Override
    public List<HotelResponseDTO> findByCity(
            String city
    ) {

        if (!hotelRepository.existsByCityIgnoreCase(city)) {

            throw new HotelNotFoundException(
                    "Não há hotel cadastrado na cidade: "
                            + city
            );
        }

        return hotelMapper.toResponseList(
                hotelRepository.findAllByCityIgnoreCase(city)
        );
    }


    @Override
    public List<HotelResponseDTO> findByState(
            String state
    ) {

        if (!hotelRepository.existsByStateIgnoreCase(state)) {

            throw new HotelNotFoundException(
                    "Não há hotel cadastrado no estado: "
                            + state
            );
        }

        return hotelMapper.toResponseList(
                hotelRepository.findAllByStateIgnoreCase(state)
        );
    }


    @Override
    public List<HotelResponseDTO> findByName(
            String name
    ) {

        if (!hotelRepository.existsByNameIgnoreCase(name)) {

            throw new HotelNotFoundException(
                    "Não há hotel cadastrado com o nome: "
                            + name
            );
        }

        return hotelMapper.toResponseList(
                hotelRepository.findAllByNameIgnoreCase(name)
        );
    }


    @Override
    public List<HotelResponseDTO> findByNameContaining(
            String name
    ) {

        if (!hotelRepository
                .existsByNameContainingIgnoreCase(name)
        ) {

            throw new HotelNotFoundException(
                    "Não há hotel cadastrado com a(s) palavra(s): "
                            + name
            );
        }

        return hotelMapper.toResponseList(
                hotelRepository.findAllByNameContainingIgnoreCase(name)
        );
    }
}