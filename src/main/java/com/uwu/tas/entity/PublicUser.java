package com.uwu.tas.entity;

import com.uwu.tas.enums.Gender;
import com.uwu.tas.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
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

    private String username;
    private String password;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private boolean emailVerified;

    private String mobile;
    private LocalDate birthday;
    private String nationality;
    private String address;
    private String country;
    private String town;
    private String zipcode;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToMany(mappedBy = "publicUser")
    private List<VehicleReservation> vehicleReservations;

    @OneToMany(mappedBy = "publicUser")
    private List<RoomReservation> roomReservations;

}
