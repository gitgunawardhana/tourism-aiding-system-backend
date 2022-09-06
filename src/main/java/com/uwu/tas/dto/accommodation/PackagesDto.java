package com.uwu.tas.dto.accommodation;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PackagesDto {
    private long roomId;
    private List<PackageDto> packages;
}
