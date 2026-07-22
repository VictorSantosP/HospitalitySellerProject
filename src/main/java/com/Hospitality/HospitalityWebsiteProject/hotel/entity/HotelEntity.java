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


    @Column(unique = true)
    private String name;
    private String city;


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