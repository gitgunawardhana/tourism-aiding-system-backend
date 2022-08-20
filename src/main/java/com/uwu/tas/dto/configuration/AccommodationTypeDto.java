package com.uwu.tas.dto.configuration;

import com.uwu.tas.enums.VisibilityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationTypeDto {
    private long id;
    private String name;
    private String image;
    private VisibilityStatus status;
}
