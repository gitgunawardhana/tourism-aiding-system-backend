package com.uwu.tas.dto.vendor;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VendorAccommodationFacilityDetailsDto {

    private long accommodationId;
    private List<Long> facilities;

//    ACCESS
//    private List<Long> accessFacilities;

//    IMPORTANT_INFORMATION
//    private List<Long> importantInformationFacilities;

//    SAFETY_AND_CLEANLINESS
//    private List<Long> safetyAndCleanlinessFacilities;

//    OTHER
//    private List<Long> otherFacilities;

//    private boolean privateCheckInOut;
//    private boolean frontDesk;
//    private boolean checkIn;
//    private boolean carPark;
//    private boolean laundry;
//    private boolean petsAllowed;
//    private boolean transportAirport;
//    private boolean security;
//    private boolean individualAirConditioning;
//    private boolean cleaningSupplies;
//    private boolean freeFaceMasks;
//    private boolean cleaningProduct;
//    private boolean handSanitizer;
//    private boolean contactlessCheck;
//    private boolean cleanedByCompany;
//    private boolean parking;
//    private boolean bar;
//    private boolean smokingRoom;
//    private boolean restaurant;
//    private boolean gym;
//    private boolean swimmingPool;
}
