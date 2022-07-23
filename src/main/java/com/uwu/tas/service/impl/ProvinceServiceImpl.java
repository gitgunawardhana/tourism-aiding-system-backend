package com.uwu.tas.service.impl;

import com.uwu.tas.dto.province.ProvinceDto;
import com.uwu.tas.entity.Province;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.repository.ProvinceRepository;
import com.uwu.tas.service.ProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceRepository provinceRepository;

    @Override
    public void saveProvince(ProvinceDto provinceDto) {

        Optional<Province> optionalProvince = provinceRepository.findByName(provinceDto.getName());
        boolean present = optionalProvince.isPresent();

        if (present) {
            throw new CustomServiceException("Province name already exists!");
        }

        Province province = new Province();
        province.setName(provinceDto.getName());
        provinceRepository.save(province);
    }

    @Override
    public List<ProvinceDto> getAllProvince() {
        List<Province> provinces = provinceRepository.findAll();
        List<ProvinceDto> provinceDtos = new ArrayList<>();
        for (Province p : provinces) {
            provinceDtos.add(new ProvinceDto(p.getId(), p.getName()));
        }
        return provinceDtos;
    }

    @Override
    public List<String> getAllProvinceNames() {
        List<Province> provinces = provinceRepository.findAll();
        return provinces.stream().map(Province::getName).collect(Collectors.toList());
    }
}
