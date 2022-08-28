package com.uwu.tas.dto.accommodation;

import com.uwu.tas.enums.VisibilityStatus;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccommodationDto {
    private long id;
    private String name;
    private String telephone;
    private String email;
    private double rating;
    private int ratingCount;
    private String accommodationType;
    private long numberOfAvailablePackages;
    private VisibilityStatus visibilityStatus;

    private String description;
    private String location;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String province;
    private String postalCode;
    private double latitude;
    private double longitude;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private boolean parking;
    private boolean smoking;
    private boolean pets;
    private List<String> pictures;

    public AccommodationDto(long id, String name, String telephone, String email, double rating, int ratingCount, String accommodationType, long numberOfAvailablePackages, VisibilityStatus visibilityStatus) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.accommodationType = accommodationType;
        this.numberOfAvailablePackages = numberOfAvailablePackages;
        this.visibilityStatus = visibilityStatus;
    }
}
