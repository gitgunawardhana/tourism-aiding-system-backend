package com.uwu.tas.repository;

import com.uwu.tas.entity.PublicUserVerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicUserVerificationCodeRepository extends JpaRepository<PublicUserVerificationCode, String> {
}
