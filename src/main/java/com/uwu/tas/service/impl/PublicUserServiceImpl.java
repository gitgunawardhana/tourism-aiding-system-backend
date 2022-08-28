package com.uwu.tas.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.uwu.tas.dto.publicUser.PublicUserCodeVerifyDto;
import com.uwu.tas.dto.publicUser.PublicUserDetailsDto;
import com.uwu.tas.dto.publicUser.PublicUserLogLnDetailsDto;
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
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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


    @Override
    public PublicUserDetailsDto getPublicUserDetails(String email) {

        Optional<PublicUser> optionalPublicUser = publicUserRepository.findByEmail(email);
        if (!optionalPublicUser.isPresent()) {
            throw new CustomServiceException(404, "Public user not found");
        }

        PublicUser publicUser = optionalPublicUser.get();

        PublicUserDetailsDto publicUserDetailsDto = new PublicUserDetailsDto();
        publicUserDetailsDto.setEmail(publicUser.getEmail());
        publicUserDetailsDto.setFirstName(publicUser.getFirstName());
        publicUserDetailsDto.setLastName(publicUser.getLastName());
        publicUserDetailsDto.setMobile(publicUser.getMobile());
        publicUserDetailsDto.setBirthday(publicUser.getBirthday());
        publicUserDetailsDto.setGender(publicUser.getGender());

        String username = publicUser.getUsername();
        if (username == null) {
            publicUserDetailsDto.setUsername("");
        } else {
            publicUserDetailsDto.setUsername(publicUser.getUsername());
        }

        String town = publicUser.getTown();
        if (town == null) {
            publicUserDetailsDto.setTown("");
        } else {
            publicUserDetailsDto.setTown(publicUser.getTown());
        }


        String nationality = publicUser.getNationality();
        if (nationality == null) {
            publicUserDetailsDto.setNationality("");
        } else {
            publicUserDetailsDto.setNationality(publicUser.getNationality());
        }

        String country = publicUser.getCountry();
        if (country == null) {
            publicUserDetailsDto.setCountry("");
        } else {
            publicUserDetailsDto.setCountry(publicUser.getCountry());
        }

        String zipCode = publicUser.getZipcode();
        if (zipCode == null) {
            publicUserDetailsDto.setZipcode("");
        } else {
            publicUserDetailsDto.setZipcode(publicUser.getZipcode());
        }

        String address = publicUser.getAddress();
        if (address == null) {
            publicUserDetailsDto.setAddress("");
        } else {
            publicUserDetailsDto.setAddress(publicUser.getAddress());
        }


        return publicUserDetailsDto;
    }

    @Override
    public void updateDetails(PublicUserDetailsDto publicUserDetailsDto) {
        Optional<PublicUser> optionalPublicUser = publicUserRepository.findByEmail(publicUserDetailsDto.getEmail());
        if (!optionalPublicUser.isPresent()) {
            System.out.println("ERROR: VendorService.checkVerificationCode: Verification code not found");
            throw new CustomServiceException(404, "No verification code for given vendor");
        }

        PublicUser publicUser = optionalPublicUser.get();

        publicUser.setUsername(publicUserDetailsDto.getUsername());
        publicUser.setFirstName(publicUserDetailsDto.getFirstName());
        publicUser.setLastName(publicUserDetailsDto.getLastName());
        publicUser.setMobile(publicUserDetailsDto.getMobile());
        publicUser.setBirthday(publicUserDetailsDto.getBirthday());
        publicUser.setGender(publicUserDetailsDto.getGender());
        publicUser.setNationality(publicUserDetailsDto.getNationality());
        publicUser.setCountry(publicUserDetailsDto.getCountry());
        publicUser.setAddress(publicUserDetailsDto.getAddress());
        publicUser.setTown(publicUserDetailsDto.getTown());
        publicUser.setZipcode(publicUserDetailsDto.getZipcode());

        publicUserRepository.save(publicUser);

    }

}
