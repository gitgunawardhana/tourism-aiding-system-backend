package com.uwu.tas.dto.driver;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DriverDto {

    private long id;
    private String firstName;
    private String lastName;
    private String nic;
    private String registrationNumber;
    private String mobile;
    private int age;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String province;
    private String postalCode;
}
