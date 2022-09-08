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
public class ReservationPackageDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double price;
    private double discount;
    private double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private RoomReservation roomReservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private RoomPackage roomPackage;

}
