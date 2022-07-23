package com.uwu.tas.service;

import com.uwu.tas.dto.vendor.VendorCodeVerifyDto;
import com.uwu.tas.dto.vendor.VendorRegisterDto;
import org.springframework.stereotype.Service;

@Service
public interface VendorService {
    VendorRegisterDto registerVendor(VendorRegisterDto vendorRegisterDto);

    void verifyVendor(VendorCodeVerifyDto vendorCodeVerifyDto);
}
