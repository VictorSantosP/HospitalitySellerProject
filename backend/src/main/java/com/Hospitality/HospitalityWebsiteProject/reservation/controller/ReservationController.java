package com.Hospitality.HospitalityWebsiteProject.reservation.controller;


import com.Hospitality.HospitalityWebsiteProject.hotel.dto.HotelResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.reservation.dto.ReservationRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.reservation.dto.ReservationResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.reservation.services.ReservationService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {
    @Autowired
    private final ReservationService service;

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> createReservation(@Valid @RequestBody ReservationRequestDTO dto){
        ReservationResponseDTO response = service.createReservation(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<ReservationResponseDTO>> findAll(Pageable pageable){
        Page<ReservationResponseDTO> response = service.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<ReservationResponseDTO> findById(@PathVariable Long id){
        ReservationResponseDTO response = service.findById(id);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReservationResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ReservationRequestDTO dto){
        ReservationResponseDTO response = service.updateById(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/list/room/{id}")
    public ResponseEntity<List<ReservationResponseDTO>> findAllByRoomId(@PathVariable Long id){
        List<ReservationResponseDTO> response = service.findAllByRoomId(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
