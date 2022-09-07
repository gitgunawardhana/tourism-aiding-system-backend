package com.uwu.tas.controller.admin;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.dto.vendor.VendorDto;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/admin/vendor")
@CrossOrigin
public class AdminVendorController {

    private final VendorService vendorService;

    @GetMapping(value = "")
    public ResponseEntity getAllVendors(@RequestParam(value = "text") String text) {
        try {
            List<VendorDto> allVendors = vendorService.getAllVendors(text);
            return ResponseEntity.ok(new CommonResponse<>(true, allVendors));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getVendorById(@PathVariable(value = "id") long id) {
        try {
            VendorDto vendor = vendorService.getVendorById(id);
            return ResponseEntity.ok(new CommonResponse<>(true, vendor));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }
}
