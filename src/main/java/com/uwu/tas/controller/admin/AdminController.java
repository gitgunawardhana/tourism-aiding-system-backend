package com.uwu.tas.controller.admin;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.dto.admin.AdminDto;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/admin")
@CrossOrigin
public class AdminController {

    private final AdminService adminService;

    @PostMapping(value = "/create")
    public ResponseEntity setDriverFarePerNight(@RequestBody AdminDto adminDto) {
        try {
           adminService.createAdminAccount(adminDto);
            return ResponseEntity.ok(new CommonResponse<>(true, "Admin account created successfully"));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }
}
