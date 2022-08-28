package com.uwu.tas.dto.accommodation;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomReservationDto {
    private String roomName;
    private int numberOfReservedRooms;
    private int numberOfGuests;
    private String bed;
    private double price;
    private double discount;
    private double total;
}
