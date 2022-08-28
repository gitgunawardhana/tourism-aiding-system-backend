package com.uwu.tas.dto.accommodation;

import com.uwu.tas.dto.publicuser.UserDto;
import com.uwu.tas.enums.PaymentMethod;
import com.uwu.tas.enums.ReservationStatus;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReservationDto {

    private long reservationId;
    private int amount;
    private int numberOfGuests;
    private int numberOfNights;
    private int numberOfRooms;
    private double totalPrice;
    private String dateTime;
    private String reservationStartDate;
    private String reservationEndDate;
    private ReservationStatus status;
    private PaymentMethod paymentMethod;

    private UserDto userDetails;

    private long vendorId;
    private String vendorName;
    private long accommodationId;
    private String accommodationName;
    private List<RoomReservationDto> roomReservationDetails;
}
