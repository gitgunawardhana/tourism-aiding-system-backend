package com.uwu.tas.controller.publicUser;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.dto.location.LocationDto;
import com.uwu.tas.dto.location.LocationNameDto;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/public-user/location")
@CrossOrigin
public class PublicUserLocationController {

    private final LocationService locationService;

    @GetMapping(value = "/all-locations")
    public ResponseEntity getAllLocations() {
        try {
            List<LocationDto> locations = locationService.getAllLocations();
            return ResponseEntity.ok(new CommonResponse<>(true, locations));
        } catch (CustomServiceException e) {
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        }
    }

    @GetMapping(value = "/top-locations")
    public ResponseEntity getTopLocations() {
        try {
            List<LocationDto> topLocations = locationService.getTopLocations();
            return ResponseEntity.ok(new CommonResponse<>(true, topLocations));
        } catch (CustomServiceException e) {
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        }
    }

    @GetMapping(value = "/names")
    public ResponseEntity getAllLocationNames() {
        try {
            List<LocationNameDto> allLocationNames = locationService.getAllLocationNames();
            return ResponseEntity.ok(new CommonResponse<>(true, allLocationNames));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }
}
