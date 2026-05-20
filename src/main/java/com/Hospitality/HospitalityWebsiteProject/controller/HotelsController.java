package com.Hospitality.HospitalityWebsiteProject.controller;

import com.Hospitality.HospitalityWebsiteProject.entity.HotelEntity;
import com.Hospitality.HospitalityWebsiteProject.services.HotelDTO;
import com.Hospitality.HospitalityWebsiteProject.services.HotelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelsController {

    @Autowired
    private HotelServices services;

    @PostMapping
    public HotelEntity createHotel(@RequestBody HotelDTO dto){
        return services.createHotel(dto);
    }

    @GetMapping
    public List<HotelEntity> getHotels(){
        return services.getHotels();
    }





}
