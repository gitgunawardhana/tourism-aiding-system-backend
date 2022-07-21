package com.uwu.tas.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class PublicUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String password;
    private String firstName;
    private String lastName;
    private String nicOrPassport;

    @Column(unique = true)
    private String email;

    private boolean emailVerified;
    private String mobile;
    private String address;
    private String country;

    @OneToMany(mappedBy = "publicUser")
    private List<VehicleReservation> vehicleReservations;

    @OneToMany(mappedBy = "publicUser")
    private List<RoomReservation> roomReservations;

}
