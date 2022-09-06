package com.uwu.tas.controller.publicUser;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.dto.accommodation.AccommodationSearchDto;
import com.uwu.tas.dto.accommodation.HousingFacilityDto;
import com.uwu.tas.dto.configuration.AccommodationTypeDto;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.service.AccommodationTypeService;
import com.uwu.tas.service.HousingFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/public-user/accommodation")
@CrossOrigin
public class PublicUserAccommodationController {

    private final AccommodationTypeService accommodationTypeService;
    private final HousingFacilityService housingFacilityService;

    @GetMapping(value = "/types")
    public ResponseEntity getAllVisibleAccommodationTypes() {
        try {
            List<AccommodationTypeDto> accommodationTypes = accommodationTypeService.getAllVisibleAccommodationTypes();
            return ResponseEntity.ok(new CommonResponse<>(true, accommodationTypes));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @GetMapping(value = "/facilities")
    public ResponseEntity getMainHousingFacilities() {
        try {
            List<HousingFacilityDto> mainHousingFacilities = housingFacilityService.getMainHousingFacilities();
            return ResponseEntity.ok(new CommonResponse<>(true, mainHousingFacilities));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @PostMapping(value = "/search")
    public ResponseEntity searchAccommodations(@RequestBody AccommodationSearchDto accommodationSearchDto) {
        try {
            System.out.println(accommodationSearchDto);
            return ResponseEntity.ok(new CommonResponse<>(true, ""));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }
}
