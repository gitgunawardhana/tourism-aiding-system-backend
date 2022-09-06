package com.uwu.tas.entity;


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

public class AccommodationFacility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    private boolean parking;
//    private boolean privateCheckInOut;
//    private boolean twentyFourHourCheckInOut;
//    private boolean frontDesk;
//    private boolean petsAllowed;
//    private boolean freeWiFi;
//    private boolean roomService;
//
//    private boolean laundry;
//    private boolean transportAirport;
//    private boolean security;
//    private boolean individualAirConditioning;
//    private boolean cleaningSupplies;
//    private boolean freeFaceMasks;
//    private boolean cleaningProduct;
//    private boolean handSanitizer;
//    private boolean contactlessCheckIn;
//    private boolean cleanedByCompany;
//
//    private boolean bar;
//    private boolean smokingRoom;
//    private boolean restaurant;
//    private boolean gym;
//    private boolean swimmingPool;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private HousingFacility housingFacility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Accommodation accommodation;


}
