package com.uwu.tas.repository;

import com.uwu.tas.entity.Vehicle;
import com.uwu.tas.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findAllByVendor(Vendor vendor);
}
