package com.uwu.tas.entity;

import com.uwu.tas.enums.GearType;
import com.uwu.tas.enums.VisibilityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private int noOfSeats;

    @Enumerated(EnumType.STRING)
    private GearType gearType;

    private boolean childSeats;
    private boolean availability;
    private boolean airConditioning;

    @Enumerated(EnumType.STRING)
    private VisibilityStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private VehicleType vehicleType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Vendor vendor;

    @OneToMany(mappedBy = "vehicle")
    private List<ReservationVehicleDetail> reservationVehicleDetails;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Driver driver;
}
