package com.Hospitality.HospitalityWebsiteProject.hotel.controller;

import com.Hospitality.HospitalityWebsiteProject.hotel.dto.HotelRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.hotel.dto.HotelResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.hotel.services.HotelServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelsController {

    private final HotelServices hotelServices;

    @Autowired
    public HotelsController(HotelServices hotelServices){
        this.hotelServices = hotelServices;
    }

    @PostMapping
    public ResponseEntity<HotelResponseDTO> createHotel(@Valid @RequestBody HotelRequestDTO hotelRequestDTO){
        HotelResponseDTO response= hotelServices.createHotel(hotelRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<HotelResponseDTO>> getAllHotels(Pageable pageable){
        Page<HotelResponseDTO> hotels = hotelServices.getAllHotels(pageable);
        return ResponseEntity.ok(hotels);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<HotelResponseDTO> findById(@PathVariable Long id){
        HotelResponseDTO response = hotelServices.getHotelById(id);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        hotelServices.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HotelResponseDTO> UpdateById(@PathVariable Long id, @Valid @RequestBody HotelRequestDTO hotelRequestDTO){
        HotelResponseDTO response = hotelServices.updateById(id, hotelRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/list/city/{city}")
    public ResponseEntity<List<HotelResponseDTO>> findByCity(@PathVariable String city){
        List<HotelResponseDTO> response = hotelServices.findByCity(city);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/list/state/{state}")
    public ResponseEntity<List<HotelResponseDTO>> findByState(@PathVariable String state){
        List<HotelResponseDTO> response = hotelServices.findByState(state);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/list/name/{name}")
    public ResponseEntity<List<HotelResponseDTO>> findByName(@PathVariable String name){
        List<HotelResponseDTO> response = hotelServices.findByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/list/name/contains/{name}")
    public ResponseEntity<List<HotelResponseDTO>> findByNameContaining(@PathVariable String name){
        List<HotelResponseDTO> response = hotelServices.findByNameContaining(name);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
