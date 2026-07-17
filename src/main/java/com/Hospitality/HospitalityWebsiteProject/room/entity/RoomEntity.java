package com.Hospitality.HospitalityWebsiteProject.room.entity;


import com.Hospitality.HospitalityWebsiteProject.hotel.entity.HotelEntity;
import com.Hospitality.HospitalityWebsiteProject.room.enums.Avaliability;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Rooms")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;

    private Integer capacity;

    private Double price;

    private Avaliability avaliable;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id")
    private HotelEntity hotelEntity;

    public RoomEntity(Long id, Integer number, Integer capacity, Double price, Avaliability avaliable, HotelEntity hotelEntity) {
        this.id = id;
        this.number = number;
        this.capacity = capacity;
        this.price = price;
        this.avaliable = avaliable;
        this.hotelEntity = hotelEntity;
    }

    public RoomEntity(){}

    public RoomEntity(Integer number, Integer capacity, Double price, Avaliability avaliable) {
        this.number = number;
        this.capacity = capacity;
        this.price = price;
        this.avaliable = avaliable;
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

    public Avaliability getAvaliable() {
        return avaliable;
    }

    public void setAvaliable(Avaliability avaliable) {
        this.avaliable = avaliable;
    }

    public HotelEntity getHotelEntity() {
        return hotelEntity;
    }

    public void setHotelEntity(HotelEntity hotelEntity) {
        this.hotelEntity = hotelEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RoomEntity that = (RoomEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(number, that.number) && Objects.equals(capacity, that.capacity) && Objects.equals(price, that.price) && Objects.equals(avaliable, that.avaliable) && Objects.equals(hotelEntity, that.hotelEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, capacity, price, avaliable, hotelEntity);
    }

}
