package com.uwu.tas.service;

import com.uwu.tas.dto.vendor.*;
import org.springframework.stereotype.Service;

@Service
public interface AccommodationService {
    VendorAccommodationBasicDetailsDto registerAccommodationBasicDetails(VendorAccommodationBasicDetailsDto vendorAccommodationBasicDetailsDto);

    VendorAccommodationLocationDetailsDto registerAccommodationLocationDetails(VendorAccommodationLocationDetailsDto vendorAccommodationLocationDetailsDto);

    VendorAccommodationHouseRuleDetails registerAccommodationHouseRuleDetails(VendorAccommodationHouseRuleDetails vendorAccommodationHouseRuleDetails);

    VendorAccommodationFacilityDetailsDto registerVendorAccommodationFacilityDetails(VendorAccommodationFacilityDetailsDto vendorAccommodationFacilityDetailsDto);

    void saveAccommodationPicture(VendorAccommodationPictureDto vendorAccommodationPictureDto);
}
