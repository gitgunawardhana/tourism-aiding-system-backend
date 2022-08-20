package com.uwu.tas.controller.admin;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.dto.location.LocationAttractionDto;
import com.uwu.tas.dto.location.LocationDto;
import com.uwu.tas.enums.VisibilityStatus;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/admin/location")
@CrossOrigin
public class AdminLocationController {

    private final LocationService locationService;

    @PostMapping(value = "")
    public ResponseEntity createLocation(@RequestBody LocationDto locationDto) {
        try {
            locationService.createLocation(locationDto);
            return ResponseEntity.ok(new CommonResponse<>(true, "Location created successfully!"));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @PutMapping(value = "")
    public ResponseEntity updateLocation(@RequestBody LocationDto locationDto) {
        try {
            locationService.updateLocation(locationDto);
            return ResponseEntity.ok(new CommonResponse<>(true, "Location updated successfully!"));
        } catch (CustomServiceException e) {
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity changeLocationStatus(@PathVariable(value = "id") long id) {
        try {
            locationService.changeLocationStatus(id);
            return ResponseEntity.ok(new CommonResponse<>(true, "Location status changed successfully!"));
        } catch (CustomServiceException e) {
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getLocationById(@PathVariable(value = "id") long id) {
        try {
            LocationDto location = locationService.getLocationById(id);
            return ResponseEntity.ok(new CommonResponse<>(true, location));
        } catch (CustomServiceException e) {
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        }
    }

    @GetMapping(value = "/name/{id}")
    public ResponseEntity getLocationNameById(@PathVariable(value = "id") long id) {
        try {
            String locationName = locationService.getLocationNameById(id);
            return ResponseEntity.ok(new CommonResponse<>(true, "Location name retrieved successfully", locationName));
        } catch (CustomServiceException e) {
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        }
    }

    @GetMapping(value = "")
    public ResponseEntity getAllLocations(@RequestParam(value = "text") String text) {
        try {
            List<LocationDto> locations = locationService.getAllLocations(text);
            return ResponseEntity.ok(new CommonResponse<>(true, locations));
        } catch (CustomServiceException e) {
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        }
    }

    @PostMapping(value = "/attraction")
    public ResponseEntity createLocationAttraction(@RequestBody LocationAttractionDto locationAttractionDto) {
        try {
            locationService.createLocationAttraction(locationAttractionDto);
            return ResponseEntity.ok(new CommonResponse<>(true, "Location attraction created successfully!"));
        } catch (CustomServiceException e) {
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        }
    }

    @PutMapping(value = "/attraction")
    public ResponseEntity updateLocationAttraction(@RequestBody LocationAttractionDto locationAttractionDto) {
        try {
            locationService.updateLocationAttraction(locationAttractionDto);
            return ResponseEntity.ok(new CommonResponse<>(true, "Location attraction updated successfully!"));
        } catch (CustomServiceException e) {
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        }
    }

    @PatchMapping(value = "/attraction/{id}")
    public ResponseEntity changeLocationAttractionStatus(@PathVariable(value = "id") long id) {
        try {
            locationService.changeLocationAttractionStatus(id);
            return ResponseEntity.ok(new CommonResponse<>(true, "Location attraction status changed successfully!"));
        } catch (CustomServiceException e) {
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        }
    }

    @GetMapping(value = "/attraction/{id}")
    public ResponseEntity getLocationAttractionById(@PathVariable(value = "id") long id) {
        try {
            LocationAttractionDto attraction = locationService.getLocationAttractionById(id);
            return ResponseEntity.ok(new CommonResponse<>(true, attraction));
        } catch (CustomServiceException e) {
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        }
    }

    @GetMapping(value = "/attraction")
    public ResponseEntity getAllLocationAttractions(@RequestParam(value = "locationId") long locationId, @RequestParam(value = "text") String text) {
        try {
            List<LocationAttractionDto> attractions = locationService.getLocationAttractionsByLocation(locationId, text);
            return ResponseEntity.ok(new CommonResponse<>(true, attractions));
        } catch (CustomServiceException e) {
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        }
    }
}
