package com.Hospitality.HospitalityWebsiteProject.hotel.entity;


import com.Hospitality.HospitalityWebsiteProject.room.entity.RoomEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "Hotels")
public class HotelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 200, message = "Nome maior que 200 ou menor que 0")
    @Column(unique = true)
    private String name;
    @NotBlank(message = "City name is mandatory")
    @Size(min = 3, max = 50, message = "Valor inválido, maior que 50 e menor que 3")
    private String city;
    @NotBlank(message = "State name is mandatory")
    @Size(min = 3, max = 50, message = "Valor inválido, maior que 50 e menor que 3")
    private String state;
    @Positive
    private Double pricePerDay;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hotelEntity", cascade = CascadeType.ALL)
    private List<RoomEntity> rooms;

    public HotelEntity(Long id, String name, String city, String state, Double pricePerDay, List<RoomEntity> rooms) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.state = state;
        this.pricePerDay = pricePerDay;
        this.rooms = rooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public List<RoomEntity> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomEntity> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Hotel " + name
                + "Id:" + id
                + "Cidade e Estado"
                + city + "-" + state
                + String.format("%.2f",pricePerDay);
    }
}
