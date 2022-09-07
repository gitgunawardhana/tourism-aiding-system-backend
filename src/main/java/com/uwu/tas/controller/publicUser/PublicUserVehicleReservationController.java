package com.uwu.tas.controller.publicUser;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.dto.publicUser.PublicUserRegistrationDto;
import com.uwu.tas.dto.publicUser.VehicleReservationDto;
import com.uwu.tas.entity.ReservationVehicleDetail;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.service.PublicUserService;
import com.uwu.tas.service.VehicleReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping(value = "/public-user")
public class PublicUserVehicleReservationController {

    private final VehicleReservationService vehicleReservationService;

    @PostMapping(value = "/vehicle-reservation")
    public ResponseEntity saveReservationVehicleDetail(@RequestBody VehicleReservationDto vehicleReservationDto){
        try {
            ReservationVehicleDetail result = vehicleReservationService.saveReservationVehicleDetail(vehicleReservationDto);
            return ResponseEntity.ok(new CommonResponse<>(true, result));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }
}
