package com.uwu.tas.controller.admin;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.dto.accommodation.AccommodationDto;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/admin/accommodation")
@CrossOrigin
public class AdminAccommodationController {

    private final AccommodationService accommodationService;

    @GetMapping(value = "/{id}")
    public ResponseEntity getAllVendors(@PathVariable(value = "id") long id) {
        try {
            AccommodationDto accommodation = accommodationService.getAccommodationById(id);
            return ResponseEntity.ok(new CommonResponse<>(true, accommodation));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }
}
