package com.uwu.tas.service.impl;

import com.uwu.tas.dto.vehicle.VehicleDto;
import com.uwu.tas.entity.Vendor;
import com.uwu.tas.enums.AvailabilityStatus;
import com.uwu.tas.enums.VendorType;
import com.uwu.tas.repository.VehicleRepository;
import com.uwu.tas.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Override
    public List<VehicleDto> getVehiclesForVendor(Vendor vendor) {
        if (!vendor.getType().equals(VendorType.ACCOMMODATION)) {
            return vehicleRepository.findAllByVendor(vendor).stream()
                    .map(vehicle -> new VehicleDto(vehicle.getId(),
                            vehicle.getName(),
                            vehicle.getNoOfSeats(),
                            vehicle.getGearType(),
                            vehicle.isAvailability() ? AvailabilityStatus.AVAILABLE : AvailabilityStatus.NOT_AVAILABLE,
                            vehicle.getStatus(),
                            vehicle.getVehicleType().getName(),
                            vehicle.getLocation().getName())).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }
}
