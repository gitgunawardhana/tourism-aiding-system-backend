package com.uwu.tas.repository;

import com.uwu.tas.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    boolean existsByEmail(String email);

    Optional<Vendor> findByEmail(String email);

    @Query(value = "SELECT v FROM Vendor v " +
            "WHERE v.firstName LIKE %:text% " +
            "OR v.lastName LIKE %:text% " +
            "OR v.email LIKE %:text% " +
            "OR v.mobile LIKE %:text%")
    List<Vendor> findByText(@Param("text") String text);
}
