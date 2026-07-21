package com.Hospitality.HospitalityWebsiteProject.hotel.entity;

import com.Hospitality.HospitalityWebsiteProject.room.entity.RoomEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Hotels")
@Getter
@NoArgsConstructor
@Setter
public class HotelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(
            min = 2,
            max = 200,
            message = "Nome maior que 200 ou menor que 2"
    )
    @Column(unique = true)
    private String name;

    @NotBlank(message = "City name is mandatory")
    @Size(
            min = 3,
            max = 50,
            message = "Valor inválido, maior que 50 ou menor que 3"
    )
    private String city;

    @NotBlank(message = "State name is mandatory")
    @Size(
            min = 3,
            max = 50,
            message = "Valor inválido, maior que 50 ou menor que 3"
    )
    private String state;

    @OneToMany(
            mappedBy = "hotelEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<RoomEntity> rooms = new ArrayList<>();



    public void addRoom(RoomEntity room) {
        rooms.add(room);
        room.setHotelEntity(this);
    }

    public void removeRoom(RoomEntity room) {
        rooms.remove(room);
        room.setHotelEntity(null);
    }



}