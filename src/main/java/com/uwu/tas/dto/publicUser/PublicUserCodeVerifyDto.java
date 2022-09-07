package com.uwu.tas.dto.publicUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PublicUserCodeVerifyDto {
    private String email;
    private String code;

}
