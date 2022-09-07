package com.uwu.tas.service.impl;

import com.uwu.tas.dto.publicUser.VehicleReservationDto;
import com.uwu.tas.entity.PublicUser;
import com.uwu.tas.entity.ReservationVehicleDetail;
import com.uwu.tas.entity.Vehicle;
import com.uwu.tas.entity.VehicleReservation;
import com.uwu.tas.repository.PublicUserRepository;
import com.uwu.tas.repository.ReservationVehicleDetailRepository;
import com.uwu.tas.repository.VehicleRepository;
import com.uwu.tas.repository.VehicleReservationRepository;
import com.uwu.tas.service.VehicleReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleReservationServiceImpl implements VehicleReservationService {

    private final VehicleReservationRepository vehicleReservationRepository;
    private final PublicUserRepository publicUserRepository;
    private final ReservationVehicleDetailRepository reservationVehicleDetailRepository;
    private final VehicleRepository vehicleRepository;

    @Override
    public VehicleReservation saveVehicleReservation(VehicleReservationDto vehicleReservationDto) {

        VehicleReservation vehicleReservation = new VehicleReservation();

        vehicleReservation.setDateTime(vehicleReservationDto.getDateTime());
        vehicleReservation.setDropOffLocation(vehicleReservationDto.getDropOffLocation());
        vehicleReservation.setPickupLocation(vehicleReservationDto.getPickupLocation());
        vehicleReservation.setTotalPrice(vehicleReservationDto.getTotalPrice());
        vehicleReservation.setPublicUser(publicUserRepository.getById(vehicleReservationDto.getPublicUserId()));

        return vehicleReservationRepository.save(vehicleReservation);
    }

    @Override
    public ReservationVehicleDetail saveReservationVehicleDetail(VehicleReservationDto vehicleReservationDto) {
        ReservationVehicleDetail reservationVehicleDetail = new ReservationVehicleDetail();

        reservationVehicleDetail.setPrice(vehicleReservationDto.getTotalPrice());
        reservationVehicleDetail.setVehicleReservation(saveVehicleReservation(vehicleReservationDto));
        reservationVehicleDetail.setVehicle(vehicleRepository.getById(vehicleReservationDto.getVehicleId()));

        return reservationVehicleDetailRepository.save(reservationVehicleDetail);
    }


}
