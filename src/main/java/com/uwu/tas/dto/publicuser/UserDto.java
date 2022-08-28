package com.uwu.tas.dto.publicuser;

import com.uwu.tas.enums.Gender;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private Gender gender;
    private String nic;
    private String address;
    private String city;
    private String province;
    private String country;
}
