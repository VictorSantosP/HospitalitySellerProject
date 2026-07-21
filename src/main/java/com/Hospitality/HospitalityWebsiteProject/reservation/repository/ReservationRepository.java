package com.Hospitality.HospitalityWebsiteProject.reservation.repository;

import com.Hospitality.HospitalityWebsiteProject.reservation.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    Optional<ReservationEntity> findById (Long id);

    List<ReservationEntity> findAllByRoomId(Long roomId);
    boolean existsByRoomId(Long roomId);

    @Query("""
        SELECT r 
        FROM ReservationEntity r
        WHERE r.roomId = :roomId
        AND r.checkIn < :checkOut
        AND r.checkOut > :checkIn
""")
    Boolean existsByOverlappingReservation(@Param("roomId") Long roomId,
                                           @Param("checkIn") LocalDate checkIn,
                                           @Param("checkOut") LocalDate checkOut);


}
