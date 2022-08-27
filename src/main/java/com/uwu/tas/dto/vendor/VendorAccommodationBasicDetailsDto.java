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
    private long id;
    private String name;
    @Lob
    private String description;
    private String email;
    private String telephone;


}
