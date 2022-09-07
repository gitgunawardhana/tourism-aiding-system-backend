package com.uwu.tas.dto.vendor;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class VendorAccommodationFacilityDetailsDto {
    private long id;


    private boolean privateCheckInOut;
    private boolean frontDesk;
    private boolean checkIn;
    private boolean carPark;
    private boolean laundry;
    private boolean petsAllowed;
    private boolean transportAirport;
    private boolean security;
    private boolean individualAirConditioning;
    private boolean cleaningSupplies;
    private boolean freeFaceMasks;
    private boolean cleaningProduct;
    private boolean handSanitizer;
    private boolean contactlessCheck;
    private boolean cleanedByCompany;
    private boolean parking;
    private boolean bar;
    private boolean smokingRoom;
    private boolean restaurant;
    private boolean gym;
    private boolean swimmingPool;
}
