package com.uwu.tas.repository;

import com.uwu.tas.entity.LocationAttraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationAttractionRepository extends JpaRepository<LocationAttraction, Long> {
}
