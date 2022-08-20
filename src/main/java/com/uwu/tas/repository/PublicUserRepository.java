package com.uwu.tas.repository;

import com.uwu.tas.entity.PublicUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublicUserRepository extends JpaRepository<PublicUser, Long> {

    Optional<PublicUser> findByEmail(String email);
}
