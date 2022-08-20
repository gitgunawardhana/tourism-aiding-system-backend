package com.uwu.tas.dto.auth;

import com.uwu.tas.enums.Gender;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonUserDetails {
    private String username;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private Gender gender;
}
