package com.Hospitality.HospitalityWebsiteProject.controller;

import com.Hospitality.HospitalityWebsiteProject.entity.HotelEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospController")
public class HospitalityController {

    HotelEntity hotel = new HotelEntity();

    @RequestMapping(value = "/hotel", method = RequestMethod.GET)
    public HotelEntity hotelController (){
        return hotel;
    }

}
