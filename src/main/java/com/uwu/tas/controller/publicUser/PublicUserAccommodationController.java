package com.uwu.tas.controller.publicUser;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.dto.accommodation.*;
import com.uwu.tas.dto.configuration.AccommodationTypeDto;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.service.AccommodationService;
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
    private final AccommodationService accommodationService;

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
            List<AccommodationSearchResultDto> result = accommodationService.searchAccommodations(accommodationSearchDto);
            return ResponseEntity.ok(new CommonResponse<>(true, result));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getAccommodationById(@PathVariable("id") long id) {
        try {
            AccommodationDto accommodationById = accommodationService.getAccommodationById(id);
            return ResponseEntity.ok(new CommonResponse<>(true, accommodationById));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @PostMapping(value = "/package/search")
    public ResponseEntity searchPackagesForAccommodation(@RequestBody AccommodationSearchDto accommodationSearchDto) {
        try {
            List<AccommodationSearchResultDto> result = accommodationService.searchPackagesForAccommodation(accommodationSearchDto);
            return ResponseEntity.ok(new CommonResponse<>(true, result));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @PostMapping(value = "/package/reserve/view")
    public ResponseEntity getReservationViewDetails(@RequestBody ReservationViewRequestDto reservationViewRequestDto) {
        try {
            AccommodationReservationViewDto result = accommodationService.getReservationViewDetails(reservationViewRequestDto);
            return ResponseEntity.ok(new CommonResponse<>(true, result));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }
}
