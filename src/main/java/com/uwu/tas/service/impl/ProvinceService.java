package com.uwu.tas.service.impl;

import com.uwu.tas.dto.ProvinceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProvinceService {
    void saveProvince(ProvinceDto provinceDto);

    List<ProvinceDto> getAllProvince();
}
