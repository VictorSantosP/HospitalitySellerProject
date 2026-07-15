package com.Hospitality.HospitalityWebsiteProject.repository;

import com.Hospitality.HospitalityWebsiteProject.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<HotelEntity, Long> {
    Optional<HotelEntity> findById(Long id);
    Boolean existsByName(String name);
}
