package com.Hospitality.HospitalityWebsiteProject.reservation.services;

import com.Hospitality.HospitalityWebsiteProject.exception.DataIntegrityException;
import com.Hospitality.HospitalityWebsiteProject.exception.ReservationAlreadyExists;
import com.Hospitality.HospitalityWebsiteProject.exception.RoomNotFoundException;
import com.Hospitality.HospitalityWebsiteProject.reservation.dto.ReservationRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.reservation.dto.ReservationResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.reservation.entity.ReservationEntity;
import com.Hospitality.HospitalityWebsiteProject.reservation.mapper.ReservationMapper;
import com.Hospitality.HospitalityWebsiteProject.reservation.repository.ReservationRepository;
import com.Hospitality.HospitalityWebsiteProject.room.entity.RoomEntity;
import com.Hospitality.HospitalityWebsiteProject.room.mapper.RoomMapper;
import com.Hospitality.HospitalityWebsiteProject.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private final ReservationMapper reservationMapper;
    @Autowired
    private final ReservationRepository reservationRepository;
    @Autowired
    private final RoomRepository roomRepository;

    @Override
    public ReservationResponseDTO createRoom(ReservationRequestDTO requestDTO) {
        RoomEntity room = roomRepository.findById(requestDTO.room_id()).
                orElseThrow(() -> new RoomNotFoundException(
                        "Quarto não encontrado com o ID: " + requestDTO.room_id()
        ));

        try{
            if (reservationRepository.existsByOverlappingReservation(requestDTO.room_id(),
                    requestDTO.checkIn(),
                    requestDTO.checkOut())) {
                throw new ReservationAlreadyExists(
                        "Quarto não possui reserva disponivel no intervalo informado."
                );
            }
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityException(
                    "Erro de integridade de dados."
            );
        }
        return null;
    }

    @Override
    public Page<ReservationResponseDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public ReservationResponseDTO findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public ReservationResponseDTO updateById(Long id, ReservationRequestDTO requestDTO) {
        return null;
    }

    @Override
    public List<ReservationEntity> findAllByRoomId(Long id) {
        return List.of();
    }
}
