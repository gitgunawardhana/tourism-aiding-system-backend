package com.uwu.tas.service.impl;

import com.uwu.tas.dto.vendor.VendorAccommodationBasicDetailsDto;
import com.uwu.tas.entity.Accommodation;
import com.uwu.tas.entity.Vendor;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.repository.AccommodationRepository;
import com.uwu.tas.repository.VendorRepository;
import com.uwu.tas.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccommodationServiceImpl implements AccommodationService {
    private final VendorRepository vendorRepository;
    private final AccommodationRepository accommodationRepository;

    @Override
    public VendorAccommodationBasicDetailsDto registerAccommodation(VendorAccommodationBasicDetailsDto vendorAccommodationBasicDetailsDto){
        System.out.println(vendorAccommodationBasicDetailsDto.getId());
        Optional<Vendor> optionalVendor = vendorRepository.findById(vendorAccommodationBasicDetailsDto.getId());

        if (!optionalVendor.isPresent()){
            throw new CustomServiceException(404, "Vendor not found");
        }
        Accommodation accommodation = new Accommodation();
        accommodation.setName(vendorAccommodationBasicDetailsDto.getName());
        accommodation.setDescription(vendorAccommodationBasicDetailsDto.getDescription());
        accommodation.setEmail(vendorAccommodationBasicDetailsDto.getEmail());
        accommodation.setTelephone(vendorAccommodationBasicDetailsDto.getTelephone());


        accommodationRepository.save(accommodation);

        return vendorAccommodationBasicDetailsDto;

    }


}
