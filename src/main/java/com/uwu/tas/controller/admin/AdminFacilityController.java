package com.uwu.tas.controller.admin;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.dto.configuration.FacilityDto;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.service.FacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/admin/facility")
@CrossOrigin
public class AdminFacilityController {

    private final FacilityService facilityService;

    @PostMapping(value = "")
    public ResponseEntity createFacility(@RequestBody FacilityDto facilityDto) {
        try {
            facilityService.createFacility(facilityDto);
            return ResponseEntity.ok(new CommonResponse<>(true, "Facility saved successfully!"));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @PutMapping(value = "")
    public ResponseEntity updateFacility(@RequestBody FacilityDto facilityDto) {
        try {
            facilityService.updateFacility(facilityDto);
            return ResponseEntity.ok(new CommonResponse<>(true, "Facility updated successfully!"));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity changeFacilityStatus(@PathVariable(value = "id") long id) {
        try {
            facilityService.changeFacilityStatus(id);
            return ResponseEntity.ok(new CommonResponse<>(true, "Facility status changed successfully!"));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteFacility(@PathVariable(value = "id") long id) {
        try {
            facilityService.deleteFacility(id);
            return ResponseEntity.ok(new CommonResponse<>(true, "Facility deleted successfully!"));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @GetMapping(value = "")
    public ResponseEntity getAllFacilities() {
        try {
            List<FacilityDto> allFacilities = facilityService.getAllFacilities();
            return ResponseEntity.ok(new CommonResponse<>(true, allFacilities));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }
}
