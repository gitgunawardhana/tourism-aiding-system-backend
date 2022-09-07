package com.uwu.tas.service.impl;

import com.uwu.tas.dto.admin.AdminDto;
import com.uwu.tas.entity.Admin;
import com.uwu.tas.enums.UserStatus;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.repository.AdminRepository;
import com.uwu.tas.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createAdminAccount(AdminDto adminDto) {
        Optional<Admin> optionalAdmin = adminRepository.findByUsername(adminDto.getUsername());
        if (optionalAdmin.isPresent()) throw new CustomServiceException("Invalid username");
        Admin admin = new Admin();
        admin.setUsername(adminDto.getUsername());
        admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        admin.setStatus(UserStatus.ACTIVE);
        adminRepository.save(admin);
    }
}
