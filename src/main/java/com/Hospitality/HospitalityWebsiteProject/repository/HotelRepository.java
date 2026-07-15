package com.Hospitality.HospitalityWebsiteProject.repository;

import com.Hospitality.HospitalityWebsiteProject.entity.HotelEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {

    Optional<HotelEntity> findById(Long id);
    Boolean existsByName(String name);

    Boolean existsByCityIgnoreCase(String city);
    List<HotelEntity> findAllByCityIgnoreCase(String city);

    Boolean existsByStateIgnoreCase(String State);
    List<HotelEntity> findAllByStateIgnoreCase(String state);

    Boolean existsByNameIgnoreCase(String name);
    List<HotelEntity> findAllByNameIgnoreCase(String name);

    Boolean existsByNameContainingIgnoreCase(String name);
    List<HotelEntity> findAllByNameContainingIgnoreCase(String name);

    Boolean existsByPricePerDayLessThan(Double price);
    List<HotelEntity> findAllByPricePerDayLessThan(Double price);

    Boolean existsByPricePerDayGreaterThan(Double price);
    List<HotelEntity> findAllByPricePerDayGreaterThan(Double price);

    Boolean existsByPricePerDayBetween(Double min, Double max);
    List<HotelEntity> findAllByPricePerDayBetween(Double min, Double max);
}
