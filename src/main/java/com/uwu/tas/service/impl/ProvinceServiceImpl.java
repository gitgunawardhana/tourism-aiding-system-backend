package com.uwu.tas.service.impl;

import com.uwu.tas.dto.configuration.ProvinceDto;
import com.uwu.tas.entity.Province;
import com.uwu.tas.enums.VisibilityStatus;
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
        province.setStatus(VisibilityStatus.VISIBLE);
        provinceRepository.save(province);
    }

    @Override
    public List<ProvinceDto> getAllProvince() {
        List<Province> provinces = provinceRepository.findAll();
        List<ProvinceDto> provinceDtos = new ArrayList<>();
        for (Province p : provinces) {
            provinceDtos.add(new ProvinceDto(p.getId(), p.getName(), p.getStatus()));
        }
        return provinceDtos;
    }

    @Override
    public List<String> getAllProvinceNames() {
        List<Province> provinces = provinceRepository.findAll();
        return provinces.stream().map(Province::getName).collect(Collectors.toList());
    }


    @Override
    public void changeProvinceStatus(long id) {
        Province province = provinceRepository.findById(id).orElseThrow(() -> new CustomServiceException(404, "Province not found"));
        VisibilityStatus status = VisibilityStatus.VISIBLE;
        if (province.getStatus().equals(VisibilityStatus.VISIBLE)) status = VisibilityStatus.NOT_VISIBLE;
        if (status.equals(VisibilityStatus.NOT_VISIBLE)){
            if (province.getLocations().size() > 0) {
                throw new CustomServiceException("Province status can not be changed. There are locations under this province");
            }
        }
        province.setStatus(status);
        provinceRepository.save(province);
    }

    @Override
    public void updateProvince(ProvinceDto provinceDto) {
        Province province = provinceRepository.findById(provinceDto.getId()).orElseThrow(() -> new CustomServiceException("Province not found"));
        Optional<Province> optionalProvince = provinceRepository.findByName(provinceDto.getName());
        if (optionalProvince.isPresent()) {
            if (province.getId() != optionalProvince.get().getId()) {
                throw new CustomServiceException("Province already existing under given name");
            }
        }
        province.setName(provinceDto.getName());
        provinceRepository.save(province);
    }

    @Override
    public void deleteProvince(long id) {
        Province province = provinceRepository.findById(id).orElseThrow(() -> new CustomServiceException("Vehicle type not found"));
        if (province.getLocations().size() > 0) {
            throw new CustomServiceException("Province cannot be deleted. There are locations under this province");
        }
        provinceRepository.delete(province);
    }
}
