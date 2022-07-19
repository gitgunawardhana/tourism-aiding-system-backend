package com.uwu.tas.service.impl.impl;

import com.uwu.tas.dto.ProvinceDto;
import com.uwu.tas.entity.Province;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.repository.ProvinceRepository;
import com.uwu.tas.service.impl.ProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceRepository provinceRepository;

    @Override
    public void saveProvince(ProvinceDto provinceDto) {
        Optional<Province> optionalProvince = provinceRepository.findByName(provinceDto.getName());
        boolean present = optionalProvince.isPresent();

        if(present){
            throw new CustomServiceException("Province name already exists!");
        }
        Province province = new Province();
        province.setName(provinceDto.getName());
        provinceRepository.save(province);
    }

    @Override
    public List<ProvinceDto> getAllProvince(){
        List<Province> provinces = provinceRepository.findAll();
        List<ProvinceDto> provinceDtos = new ArrayList<>();
        for (Province p:provinces){
            provinceDtos.add(new ProvinceDto(p.getId(),p.getName()));
        }
        return provinceDtos;
    }


}
