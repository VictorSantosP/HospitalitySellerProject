package com.Hospitality.HospitalityWebsiteProject.room.controller;

import com.Hospitality.HospitalityWebsiteProject.hotel.controller.HotelsController;
import com.Hospitality.HospitalityWebsiteProject.hotel.dto.HotelResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.hotel.services.HotelServices;
import com.Hospitality.HospitalityWebsiteProject.room.dto.RoomRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.room.dto.RoomResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.room.enums.Avaliability;
import com.Hospitality.HospitalityWebsiteProject.room.services.RoomServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomsController {

    private final RoomServices roomServices;

    @PostMapping
    public ResponseEntity<RoomResponseDTO> createHotel(@Valid @RequestBody RoomRequestDTO roomRequestDTO){
        RoomResponseDTO response = roomServices.createRoom(roomRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/list")
    public ResponseEntity<Page<RoomResponseDTO>> findAll(Pageable pageable){
        Page<RoomResponseDTO> response = roomServices.findAll(pageable);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/list/{id}")
    public ResponseEntity<RoomResponseDTO> findById (@PathVariable Long id){
        RoomResponseDTO response = roomServices.findById(id);
        return ResponseEntity.ok().body(response);
    }
    @DeleteMapping("/del/{id}")
    public ResponseEntity<RoomResponseDTO> deleteById(@PathVariable Long id){
        roomServices.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<RoomResponseDTO> updateById(@PathVariable Long id, @Valid @RequestBody RoomRequestDTO dto){
        RoomResponseDTO response = roomServices.updateById(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/list/avaliability/{avaliability}")
    public ResponseEntity<List<RoomResponseDTO>> findByAvaliability(@PathVariable Avaliability avaliability){
        List<RoomResponseDTO> response = roomServices.findByAvaliability(avaliability);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/list/avaliabilityAndHotelId/{avaliability}/{hotelId}")
    public ResponseEntity<List<RoomResponseDTO>> findByAvaliabilityAndHotelId(@PathVariable Avaliability avaliability, @PathVariable Long hotelId) {
        List<RoomResponseDTO> response = roomServices.findByAvaliabilityAndHotelId(avaliability, hotelId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/list/number/{number}")
    public ResponseEntity<List<RoomResponseDTO>> findByNumber(@PathVariable Integer number){
        List<RoomResponseDTO> response = roomServices.findByNumber(number);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/list/capacity/{capacity}")
    public ResponseEntity<List<RoomResponseDTO>> findByCapacity(@PathVariable Integer capacity){
        List<RoomResponseDTO> response = roomServices.findByCapacity(capacity);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/list/capacity/lessThan/{capacity}")
    public ResponseEntity<List<RoomResponseDTO>> findByCapacityLessThan(@PathVariable Integer capacity){
        List<RoomResponseDTO> response = roomServices.findByCapacityLessThan(capacity);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/list/capacity/greaterThan/{capacity}")
    public ResponseEntity<List<RoomResponseDTO>> findByCapacityGreaterThan(@PathVariable Integer capacity){
        List<RoomResponseDTO> response = roomServices.findByCapacityGreaterThan(capacity);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/list/capacity/between/{min}/{max}")
    public ResponseEntity<List<RoomResponseDTO>> findByCapacityBetween(@PathVariable Integer min, @PathVariable Integer max){
        List<RoomResponseDTO> response = roomServices.findByCapacityBetween(min, max);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/list/price/lessthan/{price}")
    public ResponseEntity<List<RoomResponseDTO>> findByPriceLessThan(@PathVariable Double price){
        List<RoomResponseDTO> response = roomServices.findByPriceLessThan(price);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/list/price/greaterthan/{price}")
    public ResponseEntity<List<RoomResponseDTO>> findByPriceGreaterThan(@PathVariable Double price){
        List<RoomResponseDTO> response = roomServices.findByPriceGreaterThan(price);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/list/price/between/{min}/{max}")
    public ResponseEntity<List<RoomResponseDTO>> findByPriceBetween(@PathVariable Double min, @PathVariable Double max){
        List<RoomResponseDTO> response = roomServices.findByPriceBetween(min, max);
        return ResponseEntity.ok(response);
    }

}
