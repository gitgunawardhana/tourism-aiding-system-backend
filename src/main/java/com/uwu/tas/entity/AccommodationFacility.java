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
    private boolean petAllowed;
    private boolean swimmingPool;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private  Accommodation accommodation;


}
