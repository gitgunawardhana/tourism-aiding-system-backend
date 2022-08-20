package com.uwu.tas.service;

import com.uwu.tas.dto.configuration.VehicleTypeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleTypeService {
    void createVehicleType(VehicleTypeDto vehicleTypeDto);

    void changeVehicleTypeStatus(long id);

    void updateVehicleType(VehicleTypeDto vehicleTypeDto);

    void deleteVehicleType(long id);

    List<VehicleTypeDto> getAllVehicleTypes();
}
