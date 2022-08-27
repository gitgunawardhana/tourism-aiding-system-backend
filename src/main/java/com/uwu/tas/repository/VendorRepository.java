package com.uwu.tas.repository;

import com.uwu.tas.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    boolean existsByEmail(String email);

    Optional<Vendor> findByEmail(String email);

    Optional <Vendor> findById(int id);

}
