package com.uwu.tas.service;

import com.uwu.tas.dto.admin.AdminDto;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    void createAdminAccount(AdminDto adminDto);
}
