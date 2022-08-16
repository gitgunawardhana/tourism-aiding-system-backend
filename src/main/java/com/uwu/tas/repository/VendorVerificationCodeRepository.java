package com.uwu.tas.repository;

import com.uwu.tas.entity.VendorVerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorVerificationCodeRepository extends JpaRepository<VendorVerificationCode, String> {

}
