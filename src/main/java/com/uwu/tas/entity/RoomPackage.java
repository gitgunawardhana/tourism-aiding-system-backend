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
public class RoomPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int noOfPeople;
    private double price;
    private double discount;
    private String bedDetails;
    private boolean payAtProperty;
    private boolean freeCancelation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Room room;
}
