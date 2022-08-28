package com.uwu.tas.service;

import com.uwu.tas.dto.accommodation.AccommodationDto;
import com.uwu.tas.entity.Vendor;
import org.springframework.stereotype.Service;
import com.uwu.tas.dto.vendor.*;

import java.util.List;

@Service
public interface AccommodationService {
    List<AccommodationDto> getAccommodationsForVendor(Vendor vendor);

    AccommodationDto getAccommodationById(long id);

    VendorAccommodationBasicDetailsDto registerAccommodationBasicDetails(VendorAccommodationBasicDetailsDto vendorAccommodationBasicDetailsDto);

    VendorAccommodationLocationDetailsDto registerAccommodationLocationDetails(VendorAccommodationLocationDetailsDto vendorAccommodationLocationDetailsDto);

    VendorAccommodationHouseRuleDetails registerAccommodationHouseRuleDetails(VendorAccommodationHouseRuleDetails vendorAccommodationHouseRuleDetails);

    VendorAccommodationFacilityDetailsDto registerVendorAccommodationFacilityDetails(VendorAccommodationFacilityDetailsDto vendorAccommodationFacilityDetailsDto);

    void saveAccommodationPicture(VendorAccommodationPictureDto vendorAccommodationPictureDto);
}
