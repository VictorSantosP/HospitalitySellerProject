package com.Hospitality.HospitalityWebsiteProject.repository;

import com.Hospitality.HospitalityWebsiteProject.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<HotelEntity, Long> {
    HotelEntity findByName(String name);
}
