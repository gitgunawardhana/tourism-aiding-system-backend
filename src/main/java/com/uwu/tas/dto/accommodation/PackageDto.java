package com.uwu.tas.dto.accommodation;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PackageDto {

    private int numberOfPeople;
    private double price;
    private double discount;
    private String bedDetails;
    private boolean payAtProperty;
    private boolean freeCancellation;

    private long id;
}
