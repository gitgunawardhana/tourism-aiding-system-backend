package com.uwu.tas.service;

import com.uwu.tas.dto.publicUser.PublicUserRegistrationDto;
import org.springframework.stereotype.Service;

@Service
public interface PublicUserService {

    PublicUserRegistrationDto registerPublicUser(PublicUserRegistrationDto registrationDto);
}
