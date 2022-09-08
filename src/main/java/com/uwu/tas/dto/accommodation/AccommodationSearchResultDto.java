package com.uwu.tas.dto.accommodation;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccommodationSearchResultDto {
    private long packageId;
    private long accommodationId;
    private String accommodationName;
    private String roomName;
    private String address;
    private List<String> pictures;
    private List<String> housingFacilities;
    private String size;
    private String bedDetail;
    private String description;
    private boolean freeCancellation;
    private boolean payAtProperty;
    private boolean breakfastIncluded;
    private String view;
    private double rating;
    private int ratingCount;
    private String ratingMessage;
    private double discount;
    private double price;
    private double discountedPrice;
    private int noOfPeople;
    private int noOfNights;
    private int availableCount;
    public List<Integer> availableCountList;

    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
        availableCountList = new ArrayList<>();
        for (int i = 1; i <= availableCount; i++) {
            availableCountList.add(i);
        }
    }
}
