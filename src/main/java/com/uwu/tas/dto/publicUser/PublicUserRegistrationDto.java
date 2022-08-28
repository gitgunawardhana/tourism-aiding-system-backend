package com.uwu.tas.dto.publicUser;

import com.uwu.tas.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublicUserRegistrationDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String password;
}
