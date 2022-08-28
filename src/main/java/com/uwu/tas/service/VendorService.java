package com.uwu.tas.service;

import com.uwu.tas.dto.vendor.VendorCodeVerifyDto;
import com.uwu.tas.dto.vendor.VendorDto;
import com.uwu.tas.dto.vendor.VendorRegisterDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VendorService {
    VendorRegisterDto registerVendor(VendorRegisterDto vendorRegisterDto);

    void verifyVendor(VendorCodeVerifyDto vendorCodeVerifyDto);

    List<VendorDto> getAllVendors(String text);

    VendorDto getVendorById(long id);
}
