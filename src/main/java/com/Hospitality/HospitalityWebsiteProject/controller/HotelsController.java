package com.Hospitality.HospitalityWebsiteProject.controller;

import com.Hospitality.HospitalityWebsiteProject.DTO.HotelRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.DTO.HotelResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.entity.HotelEntity;
import com.Hospitality.HospitalityWebsiteProject.services.HotelServiceImpl;
import com.Hospitality.HospitalityWebsiteProject.services.HotelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<HotelResponseDTO> createHotel(@RequestBody HotelRequestDTO hotelRequestDTO){
        HotelResponseDTO response= hotelServices.createHotel(hotelRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<HotelResponseDTO>> getAllHotels(){
        List<HotelResponseDTO> hotels = hotelServices.getAllHotels();
        return ResponseEntity.ok(hotels);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<HotelResponseDTO> findById(@PathVariable Long id){
        HotelResponseDTO response = hotelServices.getHotelById(id);
        return ResponseEntity.ok().body(response);
    }




}
