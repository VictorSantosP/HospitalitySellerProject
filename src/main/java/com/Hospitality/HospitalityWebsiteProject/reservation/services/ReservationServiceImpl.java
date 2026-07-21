package com.Hospitality.HospitalityWebsiteProject.reservation.services;

import com.Hospitality.HospitalityWebsiteProject.exception.DataIntegrityException;
import com.Hospitality.HospitalityWebsiteProject.exception.ReservationAlreadyExists;
import com.Hospitality.HospitalityWebsiteProject.exception.ReservationNotFoundException;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private final ReservationMapper reservationMapper;
    @Autowired
    private final ReservationRepository reservationRepository;
    @Autowired
    private final RoomRepository roomRepository;

    @Override
    public ReservationResponseDTO createReservation(ReservationRequestDTO requestDTO) {
        RoomEntity room = roomRepository.findById(requestDTO.room_id()).
                orElseThrow(() -> new RoomNotFoundException(
                        "Quarto não encontrado com o ID: " + requestDTO.room_id()
                ));
        if (reservationRepository.existsByOverlappingReservation(requestDTO.room_id(),
                requestDTO.checkIn(),
                requestDTO.checkOut())) {
            throw new ReservationAlreadyExists(
                    "Quarto não possui reserva disponivel no intervalo informado."
            );
        }
        try {

                ReservationEntity reservation = reservationMapper.toEntity(requestDTO);
                reservation.setRoom(room);
                ReservationEntity saved = reservationRepository.saveAndFlush(reservation);

                return reservationMapper.toResponseDTO(saved);

            } catch (DataIntegrityViolationException e) {
                throw new DataIntegrityException(
                        "Erro de integridade de dados."
                );
            }

     }

    @Override
    public Page<ReservationResponseDTO> findAll(Pageable pageable) {
        Page<ReservationEntity> reservations = reservationRepository.findAll(pageable);
        return reservations.map(reservationMapper::toResponseDTO);
    }

    @Override
    public ReservationResponseDTO findById(Long id) {
        ReservationEntity reservation = reservationRepository.findById(id).
                orElseThrow(() -> new ReservationNotFoundException(
                        "Reserva não encontrada com o ID: " + id
                ));
        return reservationMapper.toResponseDTO(reservation);
    }

    @Override
    public void deleteById(Long id) {
        if(!reservationRepository.existsById(id)){
            throw new ReservationNotFoundException(
                    "Reserva não encontrada com o ID: " + id
            );
        }
        try{
            roomRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException(
                    "Erro de integridade de dados."
            );
        }

    }

    @Override
    public ReservationResponseDTO updateById(Long id, ReservationRequestDTO requestDTO) {
        ReservationEntity reservation = reservationRepository.findById(id).
                orElseThrow(() -> new ReservationNotFoundException(
                        "Reservation não encontrado com o ID:" + id
                ));
        if(reservationRepository.existsByOverlappingReservation(id,
                requestDTO.checkIn(),
                requestDTO.checkOut())){
            throw new ReservationAlreadyExists(
                    "O quarto já está reservado na data informada."
            );
        }

        if(!reservation.getCheckIn().equals(requestDTO.checkIn())){
            reservation.setCheckIn(requestDTO.checkIn());
        }
        if(!reservation.getCheckOut().equals(requestDTO.checkOut())){
            reservation.setCheckOut(requestDTO.checkOut());
        }

        ReservationEntity saved = reservationRepository.saveAndFlush(reservation);

        return reservationMapper.toResponseDTO(saved);
    }

    @Override
    public List<ReservationResponseDTO> findAllByRoomId(Long id) {
        List<ReservationEntity> roomsReservations = roomRepository.findAllById(id);
        return roomsReservations.stream().
                map(reservationMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
