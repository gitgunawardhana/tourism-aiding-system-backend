package com.uwu.tas.dto.accommodation;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReservationRequestDto {

    private long userId;
    private List<SelectedRoomPackageDto> selectedRoomPackages;
    private LocalDate startDate;
    private LocalDate endDate;
}
