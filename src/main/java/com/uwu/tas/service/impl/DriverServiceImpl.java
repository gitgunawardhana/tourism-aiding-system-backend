package com.uwu.tas.service.impl;

import com.uwu.tas.entity.DriverFarePerNight;
import com.uwu.tas.repository.DriverFarePerNightRepository;
import com.uwu.tas.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverFarePerNightRepository driverFarePerNightRepository;

    @Override
    public void setDriverFarePerNight(double price){
        DriverFarePerNight driverFarePerNight = driverFarePerNightRepository.save(new DriverFarePerNight(1, BigDecimal.valueOf(price)));
    }

    @Override
    public double getDriverFarePerNight(){
        DriverFarePerNight driverFarePerNight = driverFarePerNightRepository.getById(1L);
        return driverFarePerNight.getPrice().doubleValue();
    }

}
