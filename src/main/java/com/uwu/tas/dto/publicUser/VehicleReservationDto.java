package com.uwu.tas.dto.publicUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VehicleReservationDto {
    private long publicUserId;
    private long vehicleId;
    private String email;
    private String mobile;
    private LocalDateTime dateTime;
    private String dropOffLocation;
    private String pickupLocation;
    private long totalPrice;
}
