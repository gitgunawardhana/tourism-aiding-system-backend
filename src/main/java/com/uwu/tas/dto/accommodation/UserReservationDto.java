package com.uwu.tas.dto.accommodation;

import com.uwu.tas.enums.ReservationStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserReservationDto {

    private long reservationId;
    private int amount;
    private String dateTime;
    private String reservationStartDate;
    private String reservationEndDate;
    private double totalPrice;
    private ReservationStatus status;
}
