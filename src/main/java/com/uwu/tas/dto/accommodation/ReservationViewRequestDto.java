package com.uwu.tas.dto.accommodation;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReservationViewRequestDto {

    private long accommodationId;
    private List<SelectedRoomPackageDto> selectedRoomPackages;
}
