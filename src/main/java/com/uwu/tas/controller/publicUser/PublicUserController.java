package com.uwu.tas.controller.publicUser;
import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.dto.publicUser.PublicUserCodeVerifyDto;
import com.uwu.tas.dto.publicUser.PublicUserRegistrationDto;
import com.uwu.tas.dto.vendor.VendorCodeVerifyDto;
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




//    @PatchMapping(value = "/verify")
//    public ResponseEntity verifyUser(@RequestBody PublicUserCodeVerifyDto publicUserCodeVerifyDto) {
//        try {
//            publicUserService.verifyVendor(vendorCodeVerifyDto);
//            return ResponseEntity.ok(new CommonResponse<VendorRegisterDto>(true, "Verification ssuccessful"));
//        } catch (CustomServiceException ce) {
//            return ResponseEntity.ok(new CommonResponse<>(false, ce.getMessage()));
//        } catch (Exception e) {
//            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
//        }
//    }
}
