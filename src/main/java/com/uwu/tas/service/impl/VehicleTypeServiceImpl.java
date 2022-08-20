package com.uwu.tas.service.impl;

import com.uwu.tas.dto.configuration.VehicleTypeDto;
import com.uwu.tas.entity.VehicleType;
import com.uwu.tas.enums.VisibilityStatus;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.repository.VehicleTypeRepository;
import com.uwu.tas.service.VehicleTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleTypeServiceImpl implements VehicleTypeService {

    private final VehicleTypeRepository vehicleTypeRepository;

    @Override
    public void createVehicleType(VehicleTypeDto vehicleTypeDto) {
        Optional<VehicleType> optionalVehicleType = vehicleTypeRepository.findByName(vehicleTypeDto.getName());
        if (optionalVehicleType.isPresent()) {
            throw new CustomServiceException("Vehicle type already existing");
        }
        VehicleType vehicleType = new VehicleType();
        vehicleType.setName(vehicleTypeDto.getName());
        vehicleType.setRentalPricePerKm(vehicleTypeDto.getRentalPricePerKm());
        vehicleType.setStatus(VisibilityStatus.VISIBLE);
        vehicleTypeRepository.save(vehicleType);
    }

    @Override
    public void changeVehicleTypeStatus(long id) {
        VehicleType vehicleType = vehicleTypeRepository.findById(id).orElseThrow(() -> new CustomServiceException(404, "Vehicle type not found"));
        VisibilityStatus status = VisibilityStatus.VISIBLE;
        if (vehicleType.getStatus().equals(VisibilityStatus.VISIBLE)) status = VisibilityStatus.NOT_VISIBLE;
        if (status.equals(VisibilityStatus.NOT_VISIBLE)) {
            if (vehicleType.getVehicles().size() > 0) {
                throw new CustomServiceException("Vehicle type status cannot be changed. There are vehicle registrations under this type");
            }
        }
        vehicleType.setStatus(status);
        vehicleTypeRepository.save(vehicleType);
    }

    @Override
    public void updateVehicleType(VehicleTypeDto vehicleTypeDto) {
        VehicleType vehicleType = vehicleTypeRepository.findById(vehicleTypeDto.getId()).orElseThrow(() -> new CustomServiceException("Vehicle type not found"));
        Optional<VehicleType> optionalVehicleType = vehicleTypeRepository.findByName(vehicleTypeDto.getName());
        if (optionalVehicleType.isPresent()) {
            if (vehicleType.getId() != optionalVehicleType.get().getId()) {
                throw new CustomServiceException("Vehicle type already existing under given name");
            }
        }
        vehicleType.setName(vehicleTypeDto.getName());
        vehicleType.setRentalPricePerKm(vehicleTypeDto.getRentalPricePerKm());
        vehicleTypeRepository.save(vehicleType);
    }

    @Override
    public void deleteVehicleType(long id) {
        VehicleType vehicleType = vehicleTypeRepository.findById(id).orElseThrow(() -> new CustomServiceException("Vehicle type not found"));
        if (vehicleType.getVehicles().size() > 0) {
            throw new CustomServiceException("Vehicle type cannot be deleted. There are vehicle registrations under this type");
        }
        vehicleTypeRepository.delete(vehicleType);
    }

    @Override
    public List<VehicleTypeDto> getAllVehicleTypes() {
        return vehicleTypeRepository.findAll().stream().map(vehicleType ->
                new VehicleTypeDto(vehicleType.getId(),
                        vehicleType.getName(),
                        vehicleType.getRentalPricePerKm(),
                        vehicleType.getStatus())).collect(Collectors.toList());
    }
}
