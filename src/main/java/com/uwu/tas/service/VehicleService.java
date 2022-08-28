package com.uwu.tas.service;

import com.uwu.tas.dto.vehicle.VehicleDto;
import com.uwu.tas.entity.Vendor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleService {
    List<VehicleDto> getVehiclesForVendor(Vendor vendor);
}
