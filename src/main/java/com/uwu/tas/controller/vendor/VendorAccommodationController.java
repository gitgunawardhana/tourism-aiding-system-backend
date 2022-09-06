package com.uwu.tas.controller.vendor;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.dto.accommodation.PackagesDto;
import com.uwu.tas.dto.accommodation.RoomDto;
import com.uwu.tas.dto.vendor.*;
import com.uwu.tas.entity.AccommodationPicture;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/vendor/accommodation")
public class VendorAccommodationController {

    private final AccommodationService accommodationService;

    @PostMapping(value = "/register/basic")
    public ResponseEntity saveVendorAccommodationDetails(@RequestBody VendorAccommodationBasicDetailsDto vendorAccommodationBasicDetailsDto) {
        try {
            System.out.println(vendorAccommodationBasicDetailsDto.getVendorId());
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

    @PutMapping(value = "/register/location")
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

    @PutMapping(value = "/register/rules")
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


    @PutMapping(value = "/register/facilities")
    public ResponseEntity saveVendorAccommodationFacilityDetails(@RequestBody VendorAccommodationFacilityDetailsDto vendorAccommodationFacilityDetailsDto) {
        try {

            accommodationService.registerVendorAccommodationFacilityDetails(vendorAccommodationFacilityDetailsDto);
            return ResponseEntity.ok(new CommonResponse<>(true, "Facilities Saved Successfully"));
        } catch (CustomServiceException ce) {
            return ResponseEntity.ok(new CommonResponse<>(false, ce.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }

    }

    @PutMapping(value = "/register/pictures")
    public ResponseEntity saveVendorAccommodationPicturesDetails(@RequestBody VendorAccommodationPictureDto vendorAccommodationPictureDto) {
        try {
            accommodationService.saveAccommodationPicture(vendorAccommodationPictureDto);
            return ResponseEntity.ok(new CommonResponse<>(true,"Picture Saved Successfully!"));
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

    @PutMapping(value = "/register/room")
    public ResponseEntity saveRoom(@RequestBody RoomDto room) {
        try {
            accommodationService.saveRoom(room);
            return ResponseEntity.ok(new CommonResponse<>(true,"Room Saved Successfully!"));
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

    @PutMapping(value = "/register/room/packages")
    public ResponseEntity saveRoomPackages(@RequestBody PackagesDto packages) {
        try {
            accommodationService.saveRoomPackages(packages);
            return ResponseEntity.ok(new CommonResponse<>(true,"Room Packages Saved Successfully!"));
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
