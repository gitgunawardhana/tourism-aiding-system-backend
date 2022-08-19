package com.uwu.tas.controller.admin;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.dto.configuration.BathroomFacilityDto;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.service.BathroomFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/admin/bathroom-facility")
@CrossOrigin
public class AdminBathroomFacilityController {

    private final BathroomFacilityService bathroomFacilityService;

    @PostMapping(value = "")
    public ResponseEntity createBathroomFacility(@RequestBody BathroomFacilityDto bathroomFacilityDto) {
        try {
            bathroomFacilityService.createBathroomFacility(bathroomFacilityDto);
            return ResponseEntity.ok(new CommonResponse<>(true, "Bathroom Facility saved successfully!"));
        } catch (CustomServiceException e) {
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @PutMapping(value = "")
    public ResponseEntity updateBathroomFacility(@RequestBody BathroomFacilityDto bathroomFacilityDto) {
        try {
            bathroomFacilityService.updateBathroomFacility(bathroomFacilityDto);
            return ResponseEntity.ok(new CommonResponse<>(true, "Bathroom Facility updated successfully!"));
        } catch (CustomServiceException e) {
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity changeBathroomFacilityStatus(@PathVariable(value = "id") long id) {
        try {
            bathroomFacilityService.changeBathroomFacilityStatus(id);
            return ResponseEntity.ok(new CommonResponse<>(true, "Bathroom Facility status changed successfully!"));
        } catch (CustomServiceException e) {
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteBathroomFacility(@PathVariable(value = "id") long id) {
        try {
            bathroomFacilityService.deleteBathroomFacility(id);
            return ResponseEntity.ok(new CommonResponse<>(true, "Bathroom Facility deleted successfully!"));
        } catch (CustomServiceException e) {
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @GetMapping(value = "")
    public ResponseEntity getAllBathroomFacilities() {
        try {
            List<BathroomFacilityDto> allBathroomFacilities = bathroomFacilityService.getAllBathroomFacilities();
            return ResponseEntity.ok(new CommonResponse<>(true, allBathroomFacilities));
        } catch (CustomServiceException e) {
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }
}
