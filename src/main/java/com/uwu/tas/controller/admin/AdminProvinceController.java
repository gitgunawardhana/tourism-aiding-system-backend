package com.uwu.tas.controller.admin;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.dto.province.ProvinceDto;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.service.ProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/admin/province")
public class AdminProvinceController {

    private final ProvinceService provinceService;

    @PostMapping(value = "")
    public ResponseEntity saveProvince(@RequestBody ProvinceDto provinceDto) {
        try {
            provinceService.saveProvince(provinceDto);
        } catch (CustomServiceException e) {
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        }
        return ResponseEntity.ok(new CommonResponse<>(true, "Province saved successfully!"));
    }

    @GetMapping(value = "")
    public ResponseEntity getAllProvince() {
        List<ProvinceDto> allProvince = provinceService.getAllProvince();
        return ResponseEntity.ok(new CommonResponse<List<ProvinceDto>>(true, allProvince));
    }

    @GetMapping(value = "/names")
    public ResponseEntity getAllProvinceNames() {
        List<ProvinceDto> allProvince = provinceService.getAllProvince();
        return ResponseEntity.ok(new CommonResponse<List<ProvinceDto>>(true, allProvince));
    }
}
