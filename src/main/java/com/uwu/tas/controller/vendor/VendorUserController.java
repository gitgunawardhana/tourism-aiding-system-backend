package com.uwu.tas.controller.vendor;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.dto.vendor.VendorCodeVerifyDto;
import com.uwu.tas.dto.vendor.VendorRegisterDto;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/vendor")
public class VendorUserController {

    private final VendorService vendorService;

    @PostMapping(value = "/register")
    public ResponseEntity registerVendor(@RequestBody VendorRegisterDto vendorRegisterDto) {
        try {
            VendorRegisterDto response = vendorService.registerVendor(vendorRegisterDto);
            return ResponseEntity.ok(new CommonResponse<VendorRegisterDto>(true, response));
        } catch (CustomServiceException ce) {
            return ResponseEntity.ok(new CommonResponse<>(false, ce.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @PostMapping(value = "/verify")
    public ResponseEntity verifyVendor(@RequestBody VendorCodeVerifyDto vendorCodeVerifyDto) {
        try {
            vendorService.verifyVendor(vendorCodeVerifyDto);
            return ResponseEntity.ok(new CommonResponse<VendorRegisterDto>(true, "Verification successful"));
        } catch (CustomServiceException ce) {
            return ResponseEntity.ok(new CommonResponse<>(false, ce.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

}
