package com.uwu.tas.controller.vendor;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.dto.vendor.VendorAccommodationBasicDetailsDto;
import com.uwu.tas.dto.vendor.VendorRegisterDto;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/vendor-accommodation")
public class VendorAccommodationController {

    private final AccommodationService accommodationService;

    @PostMapping(value = "/accommodation-register")
    public ResponseEntity saveVendorAccommodationDetails(@RequestBody VendorAccommodationBasicDetailsDto vendorAccommodationBasicDetailsDto){
        try {
            System.out.println(vendorAccommodationBasicDetailsDto.getId());
            VendorAccommodationBasicDetailsDto response = accommodationService.registerAccommodation(vendorAccommodationBasicDetailsDto);
            System.out.println();
            return ResponseEntity.ok(new CommonResponse<>(true, response));
        } catch (CustomServiceException ce) {
            return ResponseEntity.ok(new CommonResponse<>(false, ce.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }

    }





}
