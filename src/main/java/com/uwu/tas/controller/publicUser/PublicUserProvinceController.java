package com.uwu.tas.controller.publicUser;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.dto.configuration.ProvinceDto;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.service.ProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/public-user/province")
@CrossOrigin
public class PublicUserProvinceController {

    private final ProvinceService provinceService;

    @GetMapping(value = "")
    public ResponseEntity getAllProvinces(){
        try {
            List<ProvinceDto> allProvince = provinceService.getAllProvince();
            return ResponseEntity.ok(new CommonResponse<List<ProvinceDto>>(true, allProvince));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }
}
