package com.uwu.tas.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class RoomReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime dateTime;
    private double totalPrice;

    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private PublicUser publicUser;

    @OneToMany(mappedBy = "roomReservation")
    private List<ReservationPackageDetail> reservationPackageDetailList;
}
