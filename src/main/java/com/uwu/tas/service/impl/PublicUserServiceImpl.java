package com.uwu.tas.service.impl;

import com.uwu.tas.dto.publicUser.PublicUserRegistrationDto;
import com.uwu.tas.entity.PublicUser;
import com.uwu.tas.entity.PublicUserVerificationCode;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.repository.PublicUserRepository;
import com.uwu.tas.repository.PublicUserVerificationCodeRepository;
import com.uwu.tas.service.PublicUserService;
import com.uwu.tas.util.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PublicUserServiceImpl implements PublicUserService {

    private final PublicUserRepository publicUserRepository;
    private final PublicUserVerificationCodeRepository publicUserVerificationCodeRepository;
    private final EmailSender emailSender;


    @Override
    public PublicUserRegistrationDto registerPublicUser(PublicUserRegistrationDto registrationDto) {
        boolean emailExists = publicUserRepository.existsByEmail(registrationDto.getEmail());
        if (emailExists) {
            throw new CustomServiceException("Invalid Email");
        } else {
            PublicUser publicUser = new PublicUser();
            publicUser.setEmail(registrationDto.getEmail());
            publicUser.setFirstName(registrationDto.getFirstName());
            publicUser.setLastName(registrationDto.getLastName());
            publicUser.setPassword(registrationDto.getPassword());

            //send verification email
            sendVerificationCode(registrationDto.getEmail());

            publicUser = publicUserRepository.save(publicUser);
            registrationDto.setId(publicUser.getId());

            return registrationDto;
        }
    }

    private void sendVerificationCode(String email) {
        Optional<PublicUserVerificationCode> optionalCode = publicUserVerificationCodeRepository.findById(email);

        if (optionalCode.isPresent()) {
            PublicUserVerificationCode publicUserVerificationCode = optionalCode.get();
            if (publicUserVerificationCode.getDateTime().plusSeconds(30).isAfter(LocalDateTime.now())) {
                System.out.println("ERROR: VendorService.sendVerificationCode: Time between code generation is less than 30 seconds");
                throw new CustomServiceException("Please wait 30 seconds from the last verification code");
            }
        }
        PublicUserVerificationCode publicUserVerificationCode = new PublicUserVerificationCode();
        String code = generateVerificationCode();
        publicUserVerificationCode.setCode(code);
        publicUserVerificationCode.setEmail(email);
        publicUserVerificationCodeRepository.save(publicUserVerificationCode);

        List<String> emails = new ArrayList<>();
        emails.add(email);

        emailSender.sendEmail(emails,"Verification Code","This is your verification code for ROADSIGN "+code);

    }

    private String generateVerificationCode() {
        Random r = new Random();
        int low = 100000;
        int high = 1000000;
        int code = r.nextInt(high - low) + low;
        return String.valueOf(code);
    }
}
