package com.uwu.tas.service;

import com.uwu.tas.dto.ProvinceDto;

import java.util.List;

public interface ProvinceService {
    void saveProvince(ProvinceDto provinceDto);

    List<ProvinceDto> getAllProvince();
}
