package com.uwu.tas.service;

import com.uwu.tas.dto.vendor.VendorAccommodationBasicDetailsDto;
import com.uwu.tas.dto.vendor.VendorAccommodationLocationDetailsDto;
import org.springframework.stereotype.Service;

@Service
public interface AccommodationService {
    VendorAccommodationBasicDetailsDto registerAccommodationBasicDetails(VendorAccommodationBasicDetailsDto vendorAccommodationBasicDetailsDto);

    VendorAccommodationLocationDetailsDto registerAccommodationLocationDetails(VendorAccommodationLocationDetailsDto vendorAccommodationLocationDetailsDto);
}
