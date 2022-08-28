package com.uwu.tas.controller.publicUser;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.dto.publicUser.PublicUserCodeVerifyDto;
import com.uwu.tas.dto.publicUser.PublicUserDetailsDto;
import com.uwu.tas.dto.publicUser.PublicUserLogLnDetailsDto;
import com.uwu.tas.dto.publicUser.PublicUserRegistrationDto;
import com.uwu.tas.dto.vendor.VendorRegisterDto;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.service.PublicUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class PublicUserController {


    private final PublicUserService publicUserService;

    @PostMapping(value = "/register")
    public ResponseEntity registerPublicUser(@RequestBody PublicUserRegistrationDto registrationDto) {
        try {
            PublicUserRegistrationDto result = publicUserService.registerPublicUser(registrationDto);
            return ResponseEntity.ok(new CommonResponse<>(true, result));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @PostMapping(value = "/verify")
    public ResponseEntity verifyUser(@RequestBody PublicUserCodeVerifyDto publicUserCodeVerifyDto) {
        try {
            publicUserService.verifyUser(publicUserCodeVerifyDto);
            return ResponseEntity.ok(new CommonResponse<VendorRegisterDto>(true, "Verification successful"));
        } catch (CustomServiceException ce) {
            return ResponseEntity.ok(new CommonResponse<>(false, ce.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @GetMapping(value = "/details")
    public ResponseEntity getUserDetails(@RequestParam(value = "email") String email) {
        try {
            System.out.println(email);
            PublicUserDetailsDto result = publicUserService.getPublicUserDetails(email);
            System.out.println(result);
            return ResponseEntity.ok(new CommonResponse<>(true, result));
        } catch (CustomServiceException ce) {
            return ResponseEntity.ok(new CommonResponse<>(false, ce.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }


    @PutMapping(value = "/update-details")
    public ResponseEntity updateDetails(@RequestBody PublicUserDetailsDto publicUserDetailsDto) {
        try {
            publicUserService.updateDetails(publicUserDetailsDto);
            return ResponseEntity.ok(new CommonResponse<PublicUserDetailsDto>(true, "Update Successful "));
        } catch (CustomServiceException ce) {
            return ResponseEntity.ok(new CommonResponse<>(false, ce.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }

    }



}
