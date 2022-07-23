package com.uwu.tas.dto.location;

import com.uwu.tas.dto.activity.ActivityDto;
import com.uwu.tas.enums.VisibilityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocationDto {

    private long id;
    private String name;
    private double longitude;
    private double latitude;
    private String description;
    private int minimumSpendingDays;
    private VisibilityStatus visibilityStatus;
    private long provinceId;
    private String provinceName;
    private List<String> locationPictures;
    private long numberOfActivities;
    private List<ActivityDto> locationActivities;
    private long numberOfAttractions;
    private List<LocationAttractionDto> locationAttractions;
}
