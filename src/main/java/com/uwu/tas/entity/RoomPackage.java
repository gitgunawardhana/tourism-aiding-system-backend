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
public class RoomPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int noOfPeople;

    @Digits(integer = 9, fraction = 2)
    private BigDecimal price;

    private double discount;
    private String bedDetails;

    private boolean payAtProperty;
    private boolean freeCancellation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Room room;
}
