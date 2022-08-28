package com.uwu.tas.dto.vehicle;

import com.uwu.tas.dto.driver.DriverDto;
import com.uwu.tas.enums.AvailabilityStatus;
import com.uwu.tas.enums.GearType;
import com.uwu.tas.enums.VisibilityStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VehicleDto {
    private long id;
    private String name;
    private int numberOfSeats;
    private GearType gearType;
    private AvailabilityStatus availability;
    private VisibilityStatus visibilityStatus;
    private String vehicleType;
    private String location;

    private double pricePerDay;
    private double pricePerKilometer;

    private DriverDto driverDetails;

    public VehicleDto(long id, String name, int numberOfSeats, GearType gearType, AvailabilityStatus availability, VisibilityStatus visibilityStatus, String vehicleType, String location) {
        this.id = id;
        this.name = name;
        this.numberOfSeats = numberOfSeats;
        this.gearType = gearType;
        this.availability = availability;
        this.visibilityStatus = visibilityStatus;
        this.vehicleType = vehicleType;
        this.location = location;
    }
}
