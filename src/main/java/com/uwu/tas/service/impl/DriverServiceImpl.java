package com.uwu.tas.service.impl;

import com.uwu.tas.entity.DriverFarePerNight;
import com.uwu.tas.repository.DriverFarePerNightRepository;
import com.uwu.tas.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverFarePerNightRepository driverFarePerNightRepository;

    @Override
    public double setDriverFarePerNight(double price){
        DriverFarePerNight driverFarePerNight = driverFarePerNightRepository.save(new DriverFarePerNight(1, price));
        return driverFarePerNight.getPrice();
    }

    @Override
    public double getDriverFarePerNight(){
        DriverFarePerNight driverFarePerNight = driverFarePerNightRepository.getById(1L);
        return driverFarePerNight.getPrice();
    }

}
