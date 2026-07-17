package com.Hospitality.HospitalityWebsiteProject.room.repository;

import com.Hospitality.HospitalityWebsiteProject.room.entity.RoomEntity;
import com.Hospitality.HospitalityWebsiteProject.room.enums.Avaliability;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    Optional<RoomEntity> findById(Long id);

    Boolean existsByAvaliability (Avaliability avaliability);
    List<RoomEntity> findAllByAvaliability(Avaliability avaliability);

    Boolean existsByAvaliabilityAndHotelEntity_Id (Avaliability avaliability, Long hotelId);
    List<RoomEntity> findAllByAvaliabilityAndHotelEntity_Id(Avaliability avaliability, Long hotelId);

    Boolean existsByPriceLessThan(Double price);
    List<RoomEntity> findAllByPriceLessThan(Double price);

    Boolean existsByPriceGreaterThan(Double price);
    List<RoomEntity> findAllByPriceyGreaterThan(Double price);

    Boolean existsByPriceBetween(Double min, Double max);
    List<RoomEntity> findAllByPriceBetween(Double min, Double max);

    Boolean existsByNumber (Integer number);
    List<RoomEntity> findAllByNumber (Integer number);

    Boolean existsByCapacity (Integer capacity);
    List<RoomEntity> findAllByCapacity (Integer capacity);

    Boolean existsByCapacityLessThan(Integer capacity);
    List<RoomEntity> findAllByCapacityLessThan(Integer capacity);

    Boolean existsByCapacityGreaterThan(Integer capacity);
    List<RoomEntity> findAllByCapacityGreaterThan(Integer capacity);

    Boolean existsByCapacityBetween(Double min, Double max);
    List<RoomEntity> findAllByCapacityBetween(Double min, Double max);

}
