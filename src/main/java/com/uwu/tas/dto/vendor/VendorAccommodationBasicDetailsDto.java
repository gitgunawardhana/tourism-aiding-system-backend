package com.uwu.tas.dto.vendor;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VendorAccommodationBasicDetailsDto {
    private long vendorId;
    private long accommodationId;
    private String name;
    private String description;
    private String email;
    private String telephone;
    private long accommodationTypeId;
}
