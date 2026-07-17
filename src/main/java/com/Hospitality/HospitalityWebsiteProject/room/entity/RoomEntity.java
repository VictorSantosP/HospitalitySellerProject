package com.Hospitality.HospitalityWebsiteProject.room.entity;

import com.Hospitality.HospitalityWebsiteProject.hotel.entity.HotelEntity;
import com.Hospitality.HospitalityWebsiteProject.room.enums.Avaliability;
import jakarta.persistence.*;

@Entity
@Table(name = "Rooms")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;

    private Integer capacity;

    private Double price;

    @Enumerated(EnumType.STRING)
    private Avaliability avaliability;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private HotelEntity hotelEntity;


    public RoomEntity() {
    }

    public RoomEntity(
            Long id,
            Integer number,
            Integer capacity,
            Double price,
            Avaliability avaliability,
            HotelEntity hotelEntity
    ) {
        this.id = id;
        this.number = number;
        this.capacity = capacity;
        this.price = price;
        this.avaliability = avaliability;
        this.hotelEntity = hotelEntity;
    }

    public RoomEntity(
            Integer number,
            Integer capacity,
            Double price,
            Avaliability avaliability
    ) {
        this.number = number;
        this.capacity = capacity;
        this.price = price;
        this.avaliability = avaliability;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Avaliability getAvaliability() {
        return avaliability;
    }

    public void setAvaliability(Avaliability avaliability) {
        this.avaliability = avaliability;
    }

    public HotelEntity getHotelEntity() {
        return hotelEntity;
    }

    public void setHotelEntity(HotelEntity hotelEntity) {
        this.hotelEntity = hotelEntity;
    }
}