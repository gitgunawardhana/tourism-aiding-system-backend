package com.uwu.tas.service;

import com.uwu.tas.dto.vendor.VendorAccommodationBasicDetailsDto;
import org.springframework.stereotype.Service;

@Service
public interface AccommodationService {
    VendorAccommodationBasicDetailsDto registerAccommodation(VendorAccommodationBasicDetailsDto vendorAccommodationBasicDetailsDto);
}
