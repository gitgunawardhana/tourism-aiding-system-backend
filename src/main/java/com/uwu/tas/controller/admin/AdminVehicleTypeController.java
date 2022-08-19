package com.uwu.tas.controller.admin;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.dto.configuration.VehicleTypeDto;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.service.VehicleTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/admin/vehicle-type")
@CrossOrigin
public class AdminVehicleTypeController {

    private final VehicleTypeService vehicleTypeService;

    @PostMapping(value = "")
    public ResponseEntity createVehicleType(@RequestBody VehicleTypeDto vehicleTypeDto) {
        try {
            vehicleTypeService.createVehicleType(vehicleTypeDto);
            return ResponseEntity.ok(new CommonResponse<>(true, "Vehicle Type saved successfully!"));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @PutMapping(value = "")
    public ResponseEntity updateVehicleType(@RequestBody VehicleTypeDto vehicleTypeDto) {
        try {
            vehicleTypeService.updateVehicleType(vehicleTypeDto);
            return ResponseEntity.ok(new CommonResponse<>(true, "Vehicle Type updated successfully!"));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity changeVehicleTypeStatus(@PathVariable(value = "id") long id) {
        try {
            vehicleTypeService.changeVehicleTypeStatus(id);
            return ResponseEntity.ok(new CommonResponse<>(true, "Vehicle Type status changed successfully!"));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteVehicleType(@PathVariable(value = "id") long id) {
        try {
            vehicleTypeService.deleteVehicleType(id);
            return ResponseEntity.ok(new CommonResponse<>(true, "Vehicle Type deleted successfully!"));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @GetMapping(value = "")
    public ResponseEntity getAllVehicleTypes() {
        try {
            List<VehicleTypeDto> allVehicleTypes = vehicleTypeService.getAllVehicleTypes();
            return ResponseEntity.ok(new CommonResponse<>(true, allVehicleTypes));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }
}
