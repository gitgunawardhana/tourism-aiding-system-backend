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
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private String mobile;
    private String registrationNo;
    private String nic;
    private int age;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String province;
    private String postalCode;

    @OneToMany(mappedBy = "vehicle")
    private List<Vehicle> vehicles;
}
