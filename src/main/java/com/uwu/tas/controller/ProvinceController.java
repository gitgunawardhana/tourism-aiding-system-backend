package com.uwu.tas.controller;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.dto.ProvinceDto;
import com.uwu.tas.service.ProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/admin/province")
public class ProvinceController {

    private final ProvinceService provinceService;

    @PostMapping(value = "/save")
    public ResponseEntity<CommonResponse<String>> saveProvince(@RequestBody ProvinceDto provinceDto) {
        provinceService.saveProvince(provinceDto);
        return ResponseEntity.ok(new CommonResponse<String>(true, "Province saved successfully!"));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<CommonResponse<List<ProvinceDto>>> getAllProvince(){
        List<ProvinceDto> allProvince = provinceService.getAllProvince();
        return ResponseEntity.ok(new CommonResponse<List<ProvinceDto>>(true, allProvince));
    }

}
