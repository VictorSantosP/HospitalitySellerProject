package com.Hospitality.HospitalityWebsiteProject.hotel.repository;

import com.Hospitality.HospitalityWebsiteProject.hotel.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {

    Optional<HotelEntity> findById(Long id);
    Boolean existsByName(String name);

    Boolean existsByCityIgnoreCase(String city);
    @Query(value = """
            SELECT h FROM HotelEntity h WHERE UPPER(h.city) = UPPER(:city)
        """)
    List<HotelEntity> findAllByCityIgnoreCase(@Param("city") String city);

    Boolean existsByStateIgnoreCase(String State);
    List<HotelEntity> findAllByStateIgnoreCase(String state);

    Boolean existsByNameIgnoreCase(String name);
    List<HotelEntity> findAllByNameIgnoreCase(String name);

    Boolean existsByNameContainingIgnoreCase(String name);
    List<HotelEntity> findAllByNameContainingIgnoreCase(String name);
/*
    Boolean existsByPricePerDayLessThan(Double price);
    List<HotelEntity> findAllByPricePerDayLessThan(Double price);

    Boolean existsByPricePerDayGreaterThan(Double price);
    List<HotelEntity> findAllByPricePerDayGreaterThan(Double price);

    Boolean existsByPricePerDayBetween(Double min, Double max);
    List<HotelEntity> findAllByPricePerDayBetween(Double min, Double max);*/
}
