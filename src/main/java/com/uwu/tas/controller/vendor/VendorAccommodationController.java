package com.uwu.tas.controller.vendor;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.dto.vendor.*;
import com.uwu.tas.entity.AccommodationPicture;
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

    @PostMapping(value = "/accommodation-picture")
    public ResponseEntity saveVendorAccommodationPicturesDetails(@RequestBody VendorAccommodationPictureDto vendorAccommodationPictureDto) {
        try {
            accommodationService.saveAccommodationPicture(vendorAccommodationPictureDto);
            return ResponseEntity.ok(new CommonResponse<>(true,"Picture Saved Successful !"));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            if (ee.getMessage().startsWith("Packet for query is too large")) {
                return ResponseEntity.ok(new CommonResponse<>(false, "Given image sizes are too big"));
            }
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }
}
