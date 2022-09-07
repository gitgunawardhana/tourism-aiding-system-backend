package com.uwu.tas.controller.admin;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/admin/vehicle")
@CrossOrigin
public class AdminVehicleController {

    private final VehicleService vehicleService;

    @GetMapping(value = "/{id}")
    public ResponseEntity getAllVendors(@PathVariable(value = "id") long id) {
        try {

            return ResponseEntity.ok(new CommonResponse<>(true, null));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }
}
