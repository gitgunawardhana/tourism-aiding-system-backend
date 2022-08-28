package com.uwu.tas.controller.vendor;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.dto.vendor.*;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/vendor-accommodation")
public class VendorAccommodationController {

    private final AccommodationService accommodationService;

    @PostMapping(value = "/accommodation-register")
    public ResponseEntity saveVendorAccommodationDetails(@RequestBody VendorAccommodationBasicDetailsDto vendorAccommodationBasicDetailsDto) {
        try {
            System.out.println(vendorAccommodationBasicDetailsDto.getId());
            VendorAccommodationBasicDetailsDto response = accommodationService.registerAccommodationBasicDetails(vendorAccommodationBasicDetailsDto);
            System.out.println();
            return ResponseEntity.ok(new CommonResponse<>(true, response));
        } catch (CustomServiceException ce) {
            return ResponseEntity.ok(new CommonResponse<>(false, ce.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }

    }

    @PutMapping(value = "/accommodation-location-register")
    public ResponseEntity saveVendorAccommodationLocationDetails(@RequestBody VendorAccommodationLocationDetailsDto vendorAccommodationLocationDetailsDto) {
        try {
            VendorAccommodationLocationDetailsDto response = accommodationService.registerAccommodationLocationDetails(vendorAccommodationLocationDetailsDto);
            return ResponseEntity.ok(new CommonResponse<>(true, response));
        } catch (CustomServiceException ce) {
            return ResponseEntity.ok(new CommonResponse<>(false, ce.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @PutMapping(value = "/accommodation-house-rule-register")
    public ResponseEntity saveVendorAccommodationHouseRuleDetails(@RequestBody VendorAccommodationHouseRuleDetails vendorAccommodationHouseRuleDetails) {
        try {
            VendorAccommodationHouseRuleDetails response = accommodationService.registerAccommodationHouseRuleDetails(vendorAccommodationHouseRuleDetails);
            return ResponseEntity.ok(new CommonResponse<>(true, response));
        } catch (CustomServiceException ce) {
            return ResponseEntity.ok(new CommonResponse<>(false, ce.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }


    @PostMapping(value = "/accommodation-facility-register")
    public ResponseEntity saveVendorAccommodationFacilityDetails(@RequestBody VendorAccommodationFacilityDetailsDto vendorAccommodationFacilityDetailsDto) {
        try {

            VendorAccommodationFacilityDetailsDto response = accommodationService.registerVendorAccommodationFacilityDetails(vendorAccommodationFacilityDetailsDto);
            return ResponseEntity.ok(new CommonResponse<>(true, response));
        } catch (CustomServiceException ce) {
            return ResponseEntity.ok(new CommonResponse<>(false, ce.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }

    }
}
