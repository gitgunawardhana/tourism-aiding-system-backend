package com.uwu.tas.entity;

import com.uwu.tas.enums.VendorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String password;
    private String firstName;
    private String lastName;
    private String nic;

    @Column(unique = true)
    private String email;

    private boolean emailVerified;
    private String mobile;
    private String addressLine1;
    private String addressLine2;
    private String city;

    private String province;
    private String postalCode;

    @Enumerated(EnumType.STRING)
    private VendorType type;
}
