package com.uwu.tas.controller.admin;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/admin/driver")
@CrossOrigin
public class AdminDriverController {

    private final DriverService driverService;

    @PostMapping(value = "/fare-per-night")
    public ResponseEntity setDriverFarePerNight(@RequestParam(value = "price") double price) {
        try {
            driverService.setDriverFarePerNight(price);
            return ResponseEntity.ok(new CommonResponse<>(true, "Driver fare updated successfully"));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @GetMapping(value = "/fare-per-night")
    public ResponseEntity getDriverFarePerNight() {
        try {
            double driverFarePerNight = driverService.getDriverFarePerNight();
            return ResponseEntity.ok(new CommonResponse<>(true, driverFarePerNight));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }
}
