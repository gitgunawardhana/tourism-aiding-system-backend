package com.uwu.tas.controller.admin;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.dto.configuration.ProvinceDto;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.service.ProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/admin/province")
@CrossOrigin
public class AdminProvinceController {

    private final ProvinceService provinceService;

    @PostMapping(value = "")
    public ResponseEntity saveProvince(@RequestBody ProvinceDto provinceDto) {
        try {
            provinceService.saveProvince(provinceDto);
            return ResponseEntity.ok(new CommonResponse<>(true, "Province saved successfully!"));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }


    @PutMapping(value = "")
    public ResponseEntity updateProvince(@RequestBody ProvinceDto provinceDto) {
        try {
            provinceService.updateProvince(provinceDto);
            return ResponseEntity.ok(new CommonResponse<>(true, "Province updated successfully!"));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity changeProvinceStatus(@PathVariable(value = "id") long id) {
        try {
            provinceService.changeProvinceStatus(id);
            return ResponseEntity.ok(new CommonResponse<>(true, "Province status changed successfully!"));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteProvince(@PathVariable(value = "id") long id) {
        try {
            provinceService.deleteProvince(id);
            return ResponseEntity.ok(new CommonResponse<>(true, "Province deleted successfully!"));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @GetMapping(value = "")
    public ResponseEntity getAllProvince() {
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

    @GetMapping(value = "/names")
    public ResponseEntity getAllProvinceNames() {
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
