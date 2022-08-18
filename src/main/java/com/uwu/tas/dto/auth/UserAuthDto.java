package com.uwu.tas.dto.auth;

import com.uwu.tas.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
//public class UserAuthDto extends User implements UserDetails {
public class UserAuthDto {
    private long userId;
    private UserStatus status;
    private CommonUserDetails userDetails;
}
