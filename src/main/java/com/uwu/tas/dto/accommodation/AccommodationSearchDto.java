package com.uwu.tas.dto.accommodation;

import com.uwu.tas.enums.AccommodationSortingOption;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccommodationSearchDto {

    private long locationId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int noOfPeople;
    private int noOfRooms;
    private List<Long> accommodationTypes;
    private List<Integer> distances;
    private List<Integer> reviewScore;
    private List<Long> housingFacilities;
    private AccommodationSortingOption sortingOption;
}
