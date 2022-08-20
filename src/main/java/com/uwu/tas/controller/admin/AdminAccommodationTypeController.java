package com.uwu.tas.controller.admin;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.dto.configuration.AccommodationTypeDto;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.service.AccommodationTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/admin/accommodation-type")
@CrossOrigin
public class AdminAccommodationTypeController {

    private final AccommodationTypeService accommodationTypeService;

    @PostMapping(value = "")
    public ResponseEntity createAccommodationType(@RequestBody AccommodationTypeDto accommodationTypeDto) {
        try {
            accommodationTypeService.createAccommodationType(accommodationTypeDto);
            return ResponseEntity.ok(new CommonResponse<>(true, "Accommodation Type saved successfully!"));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @PutMapping(value = "")
    public ResponseEntity updateAccommodationType(@RequestBody AccommodationTypeDto accommodationTypeDto) {
        try {
            accommodationTypeService.updateAccommodationType(accommodationTypeDto);
            return ResponseEntity.ok(new CommonResponse<>(true, "Accommodation Type updated successfully!"));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity changeAccommodationTypeStatus(@PathVariable(value = "id") long id) {
        try {
            accommodationTypeService.changeAccommodationTypeStatus(id);
            return ResponseEntity.ok(new CommonResponse<>(true, "Accommodation Type status changed successfully!"));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteAccommodationType(@PathVariable(value = "id") long id) {
        try {
            accommodationTypeService.deleteAccommodationType(id);
            return ResponseEntity.ok(new CommonResponse<>(true, "Accommodation Type deleted successfully!"));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @GetMapping(value = "")
    public ResponseEntity getAllAccommodationTypes() {
        try {
            List<AccommodationTypeDto> allAccommodationTypes = accommodationTypeService.getAllAccommodationTypes();
            return ResponseEntity.ok(new CommonResponse<>(true, allAccommodationTypes));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }
}
