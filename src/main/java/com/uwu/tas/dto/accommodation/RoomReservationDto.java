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

    public RoomReservationDto(String roomName, int numberOfReservedRooms, double price, double discount) {
        this.roomName = roomName;
        this.numberOfReservedRooms = numberOfReservedRooms;
        this.price = price;
        this.discount = discount;
    }
}
