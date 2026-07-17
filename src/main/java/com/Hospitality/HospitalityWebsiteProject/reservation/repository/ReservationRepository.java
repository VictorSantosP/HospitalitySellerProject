package com.Hospitality.HospitalityWebsiteProject.reservation.repository;

import com.Hospitality.HospitalityWebsiteProject.reservation.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    Optional<ReservationEntity> findById (Long id);

    Boolean existsByCheckIn(LocalDate checkIn);
    List<ReservationEntity> findByCheckIn(LocalDate checkIn);

    Boolean existsByCheckOut(LocalDate checkOut);
    List<ReservationEntity> findByCheckOut(LocalDate checkOut);

    Boolean existsByCheckOutBetween(LocalDate checkOut);
    List<ReservationEntity> findByCheckOutBetween(LocalDate checkOut);
}
