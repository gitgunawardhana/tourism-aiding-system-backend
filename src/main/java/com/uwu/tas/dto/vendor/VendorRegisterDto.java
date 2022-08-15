package com.uwu.tas.dto.vendor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VendorRegisterDto {
    private long id;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private String password;
}
