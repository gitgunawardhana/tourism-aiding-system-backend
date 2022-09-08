package com.uwu.tas.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
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

    @CreationTimestamp
    private LocalDateTime dateTime;

    @Digits(integer = 9, fraction = 2)
    private BigDecimal totalPrice;

    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private PublicUser publicUser;

    @OneToMany(mappedBy = "roomReservation")
    private List<ReservationPackageDetail> reservationPackageDetailList;
}
