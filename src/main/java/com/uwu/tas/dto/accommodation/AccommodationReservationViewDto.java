package com.uwu.tas.dto.accommodation;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccommodationReservationViewDto {
    private String accommodationName;
    private String accommodationImage;
    private String accommodationAddress;
    private String description;
    private List<RoomReservationDto> selectedRooms;
}
