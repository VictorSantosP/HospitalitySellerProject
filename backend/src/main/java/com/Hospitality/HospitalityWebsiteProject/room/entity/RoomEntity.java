package com.Hospitality.HospitalityWebsiteProject.room.entity;

import com.Hospitality.HospitalityWebsiteProject.hotel.entity.HotelEntity;
import com.Hospitality.HospitalityWebsiteProject.reservation.entity.ReservationEntity;
import com.Hospitality.HospitalityWebsiteProject.room.enums.Avaliability;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Rooms")
@Getter
@Setter
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private Integer number;

    private Integer capacity;

    private Double price;

    @Enumerated(EnumType.STRING)
    private Avaliability avaliability;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private HotelEntity hotelEntity;

    @OneToMany(
            mappedBy = "room",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<ReservationEntity> reservations = new ArrayList<>();





}