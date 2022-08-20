package com.uwu.tas.controller.publicUser;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.dto.location.LocationDto;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/public-user/location")
@CrossOrigin
public class PublicUserLocationController {

    private final LocationService locationService;

    @GetMapping(value = "")
    public ResponseEntity getAllLocations() {
        try {
            List<LocationDto> locations = locationService.getAllLocations();
            return ResponseEntity.ok(new CommonResponse<>(true, locations));
        } catch (CustomServiceException e) {
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        }
    }
}
