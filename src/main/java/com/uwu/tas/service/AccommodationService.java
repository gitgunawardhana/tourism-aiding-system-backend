package com.uwu.tas.service;

import com.uwu.tas.dto.accommodation.AccommodationDto;
import com.uwu.tas.dto.accommodation.PackagesDto;
import com.uwu.tas.dto.accommodation.RoomDto;
import com.uwu.tas.entity.Vendor;
import org.springframework.stereotype.Service;
import com.uwu.tas.dto.vendor.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface AccommodationService {
    List<AccommodationDto> getAccommodationsForVendor(Vendor vendor);

    AccommodationDto getAccommodationById(long id);

    VendorAccommodationBasicDetailsDto registerAccommodationBasicDetails(VendorAccommodationBasicDetailsDto vendorAccommodationBasicDetailsDto);

    VendorAccommodationLocationDetailsDto registerAccommodationLocationDetails(VendorAccommodationLocationDetailsDto vendorAccommodationLocationDetailsDto);

    VendorAccommodationHouseRuleDetails registerAccommodationHouseRuleDetails(VendorAccommodationHouseRuleDetails vendorAccommodationHouseRuleDetails);

    void registerVendorAccommodationFacilityDetails(VendorAccommodationFacilityDetailsDto vendorAccommodationFacilityDetailsDto);

    void saveAccommodationPicture(VendorAccommodationPictureDto vendorAccommodationPictureDto);

    @Transactional
    void saveRoom(RoomDto roomDto);

    void saveRoomPackages(PackagesDto packagesDto);
}
