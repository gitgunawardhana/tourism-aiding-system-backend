package com.uwu.tas.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class VehicleReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime dateTime;
    private double totalPrice;
    private String pickupLocation;
    private String dropOffLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private PublicUser publicUser;

    @OneToMany(mappedBy = "vehicleReservation")
    private List<ReservationVehicleDetail> reservationVehicleDetails;
}
