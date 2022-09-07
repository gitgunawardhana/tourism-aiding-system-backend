package com.uwu.tas.repository;

import com.uwu.tas.entity.Accommodation;
import com.uwu.tas.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation,Long> {

    List<Accommodation> findAllByVendor(Vendor vendor);
}
