package com.uwu.tas.service.impl;

import com.uwu.tas.dto.vendor.VendorCodeVerifyDto;
import com.uwu.tas.dto.vendor.VendorRegisterDto;
import com.uwu.tas.entity.Vendor;
import com.uwu.tas.entity.VendorVerificationCode;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.repository.VendorRepository;
import com.uwu.tas.repository.VendorVerificationCodeRepository;
import com.uwu.tas.service.VendorService;
import com.uwu.tas.util.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorVerificationCodeRepository vendorVerificationCodeRepository;

    private final EmailSender emailSender;

    @Override
    @Transactional
    public VendorRegisterDto registerVendor(VendorRegisterDto vendorRegisterDto) {
        boolean exists = vendorRepository.existsByEmail(vendorRegisterDto.getEmail());
        if (exists) {
            System.out.println("ERROR: VendorService.registerVendor: Email already exists");
            throw new CustomServiceException(409, "Invalid email!");
        } else {
            Vendor vendor = new Vendor();
            vendor.setEmail(vendorRegisterDto.getEmail());
            vendor.setEmailVerified(false);
            vendor.setFirstName(vendorRegisterDto.getFirstName());
            vendor.setLastName(vendorRegisterDto.getLastName());
            //password must be encrypted
            vendor.setPassword(vendorRegisterDto.getPassword());
            vendor.setMobile(vendorRegisterDto.getMobile());

            sendVerificationCode(vendorRegisterDto.getEmail());

            vendor = vendorRepository.save(vendor);
            vendorRegisterDto.setId(vendor.getId());
            System.out.println("INFO: VendorService.registerVendor: Vendor saved successfully");
            return vendorRegisterDto;
        }
    }

    private void sendVerificationCode(String email) {

        Optional<VendorVerificationCode> optionalCode = vendorVerificationCodeRepository.findById(email);

        if (optionalCode.isPresent()) {
            if (optionalCode.get().getDateTime().plusSeconds(30).isAfter(LocalDateTime.now())) {
                System.out.println("ERROR: VendorService.sendVerificationCode: Time between code generation is less than 30 seconds");
                throw new CustomServiceException("Please wait 30 seconds from the last verification code");
            }
        }

        VendorVerificationCode vendorVerificationCode = new VendorVerificationCode();
        String code = generateVerificationCode();
        vendorVerificationCode.setCode(code);
        vendorVerificationCode.setEmail(email);
        vendorVerificationCodeRepository.save(vendorVerificationCode);
        System.out.println("INFO: VendorService.sendVerificationCode: Vendor verification code generated successfully");

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
    public void verifyVendor(VendorCodeVerifyDto vendorCodeVerifyDto) {

        Optional<Vendor> optionalVendor = vendorRepository.findByEmail(vendorCodeVerifyDto.getEmail());
        if (optionalVendor.isPresent()) {
            Optional<VendorVerificationCode> optionalCode = vendorVerificationCodeRepository.findById(vendorCodeVerifyDto.getEmail());
            if (!optionalCode.isPresent()) {
                System.out.println("ERROR: VendorService.checkVerificationCode: Verification code not found");
                throw new CustomServiceException(404, "No verification code for given vendor");
            }

            VendorVerificationCode vendorVerificationCode = optionalCode.get();

            long hours = ChronoUnit.HOURS.between(vendorVerificationCode.getDateTime(), LocalDateTime.now());
            if (hours >= 24) {
                System.out.println("ERROR: VendorService.checkVerificationCode: Verification code is expired");
                throw new CustomServiceException(403, "Verification code is expired, please re-send");
            }

            if (vendorVerificationCode.getCode() != null && vendorVerificationCode.getCode().equals(vendorCodeVerifyDto.getCode())) {
                System.out.println("INFO: VendorService.checkVerificationCode: Code verified successfully");
                Vendor vendor = optionalVendor.get();
                vendor.setEmailVerified(true);
                vendorRepository.save(vendor);
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
