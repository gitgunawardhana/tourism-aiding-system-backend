package com.uwu.tas.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ReservationPackageDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Digits(integer = 9, fraction = 2)
    private BigDecimal price;
    private double discount;
    private double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private RoomReservation roomReservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private RoomPackage roomPackage;

    public ReservationPackageDetail(@Digits(integer = 9, fraction = 2) BigDecimal price, double discount, double amount, RoomReservation roomReservation, RoomPackage roomPackage) {
        this.price = price;
        this.discount = discount;
        this.amount = amount;
        this.roomReservation = roomReservation;
        this.roomPackage = roomPackage;
    }
}
