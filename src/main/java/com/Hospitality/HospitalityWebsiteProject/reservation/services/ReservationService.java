package com.Hospitality.HospitalityWebsiteProject.reservation.services;

import com.Hospitality.HospitalityWebsiteProject.reservation.dto.ReservationRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.reservation.dto.ReservationResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.reservation.entity.ReservationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReservationService {
    ReservationResponseDTO createRoom (ReservationRequestDTO requestDTO);
    Page<ReservationResponseDTO> findAll (Pageable pageable);
    ReservationResponseDTO findById(Long id);
    void deleteById(Long id);
    ReservationResponseDTO updateById(Long id, ReservationRequestDTO requestDTO);
    List<ReservationEntity> findAllByRoomId(Long id);

}
