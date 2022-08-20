package com.uwu.tas.dto.configuration;

import com.uwu.tas.enums.VisibilityStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FacilityDto {
    private long id;
    private String name;
    private String image;
    private VisibilityStatus status;
}
