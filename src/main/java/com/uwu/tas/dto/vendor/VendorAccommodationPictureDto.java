package com.uwu.tas.dto.vendor;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VendorAccommodationPictureDto {
    private long accommodationId;
    private List<String> image;

}
