package com.uwu.tas.dto.vendor;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VendorAccommodationLocationDetailsDto {
    private long id;
    private String addressLine1;
    private String buildingNo;
    private String city;
    private String province;
    private String postalCode;
    private double distanceAirport;
    private double distanceCity;
    private double latitude;
    private double longitude;
}
