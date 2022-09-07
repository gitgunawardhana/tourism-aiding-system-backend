package com.uwu.tas.service;

import com.uwu.tas.dto.publicUser.VehicleReservationDto;
import com.uwu.tas.entity.ReservationVehicleDetail;
import com.uwu.tas.entity.VehicleReservation;
import org.springframework.stereotype.Service;

@Service
public interface VehicleReservationService {
    VehicleReservation saveVehicleReservation(VehicleReservationDto vehicleReservationDto);

    void saveReservationVehicleDetail(VehicleReservationDto vehicleReservationDto);
}
