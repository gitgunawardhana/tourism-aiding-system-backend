package com.uwu.tas.dto.configuration;

import com.uwu.tas.enums.VisibilityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProvinceDto {

    private long id;
    private String name;
    private VisibilityStatus status;
}
