package com.uwu.tas.repository;

import com.uwu.tas.entity.PublicUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicUserRepository extends JpaRepository<PublicUser, Long> {
}
