package com.uwu.tas.dto.vendor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VendorAccommodationHouseRuleDetails {
    private long accommodationId;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
}
