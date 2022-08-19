package com.uwu.tas.entity;

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
public class VehicleType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private double rentalPricePerKm;

    @Enumerated(EnumType.STRING)
    private VisibilityStatus status;

    @OneToMany(mappedBy = "vehicleType")
    private List<Vehicle> vehicles;
}
