package com.uwu.tas.service.impl;

import com.uwu.tas.dto.publicUser.PublicUserCodeVerifyDto;
import com.uwu.tas.dto.publicUser.PublicUserRegistrationDto;
import com.uwu.tas.entity.PublicUser;
import com.uwu.tas.entity.PublicUserVerificationCode;
import com.uwu.tas.entity.Vendor;
import com.uwu.tas.enums.UserStatus;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.repository.PublicUserRepository;
import com.uwu.tas.repository.PublicUserVerificationCodeRepository;
import com.uwu.tas.service.PublicUserService;
import com.uwu.tas.util.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
            publicUser.setMobile(registrationDto.getMobile());
            publicUser.setStatus(UserStatus.valueOf("PENDING"));

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

        emailSender.sendEmail(emails, "Verification Code", "This is your verification code for ROADSIGN " + code);

    }

    private String generateVerificationCode() {
        Random r = new Random();
        int low = 100000;
        int high = 1000000;
        int code = r.nextInt(high - low) + low;
        return String.valueOf(code);
    }

    @Override
    public void verifyUser(PublicUserCodeVerifyDto publicUserCodeVerifyDto) {
        Optional<PublicUser> optionalPublicUser = publicUserRepository.findByEmail(publicUserCodeVerifyDto.getEmail());
        if (optionalPublicUser.isPresent()) {
            Optional<PublicUserVerificationCode> optionalCode = publicUserVerificationCodeRepository.findById(publicUserCodeVerifyDto.getEmail());
            if (!optionalCode.isPresent()) {
                System.out.println("ERROR: VendorService.checkVerificationCode: Verification code not found");
                throw new CustomServiceException(404, "No verification code for given vendor");

            }
            PublicUserVerificationCode verificationCode = optionalCode.get();

            long hours = ChronoUnit.HOURS.between(verificationCode.getDateTime(), LocalDateTime.now());
            if (hours >= 24) {
                System.out.println("ERROR: VendorService.checkVerificationCode: Verification code is expired");
                throw new CustomServiceException(403, "Verification code is expired, please re-send");
            }

            if (verificationCode.getCode() != null && verificationCode.getCode().equals(publicUserCodeVerifyDto.getCode())) {
                System.out.println("INFO: VendorService.checkVerificationCode: Code verified successfully");
                PublicUser publicUser = optionalPublicUser.get();
                publicUser.setEmailVerified(true);
                publicUser.setStatus(UserStatus.valueOf("ACTIVE"));
                publicUserRepository.save(publicUser);
                return;
            }
            System.out.println("ERROR: VendorService.checkVerificationCode: Incorrect verification code");
            throw new CustomServiceException(401, "Incorrect verification code");

        } else {
            System.out.println("ERROR: VendorService.checkVerificationCode: Vendor not found");
            throw new CustomServiceException(404, "Vendor not found");
        }
    }


}
