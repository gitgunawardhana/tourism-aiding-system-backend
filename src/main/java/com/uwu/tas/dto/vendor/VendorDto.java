package com.uwu.tas.dto.vendor;

import com.uwu.tas.dto.accommodation.AccommodationDto;
import com.uwu.tas.dto.vehicle.VehicleDto;
import com.uwu.tas.enums.VendorType;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VendorDto {

    private long id;
    private String firstName;
    private String lastName;
    private String nic;
    private String email;
    private String mobile;
    private VendorType type;

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String province;
    private String postalCode;

    private List<AccommodationDto> providedAccommodations;
    private List<VehicleDto> providedVehicles;

    public VendorDto(long id, String firstName, String lastName, String nic, String email, String mobile, VendorType type) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nic = nic;
        this.email = email;
        this.mobile = mobile;
        this.type = type;
    }
}
