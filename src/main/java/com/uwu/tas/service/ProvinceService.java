package com.uwu.tas.service;

import com.uwu.tas.dto.configuration.ProvinceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProvinceService {
    void saveProvince(ProvinceDto provinceDto);

    List<ProvinceDto> getAllProvince();

    List<String> getAllProvinceNames();
}
