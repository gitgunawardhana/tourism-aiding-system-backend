package com.uwu.tas.repository;

import com.uwu.tas.entity.Location;
import com.uwu.tas.entity.LocationAttraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationAttractionRepository extends JpaRepository<LocationAttraction, Long> {

    List<LocationAttraction> findByLocation(Location location);

    long countByLocation(Location location);

    boolean existsByName(String name);

    List<LocationAttraction> findByNameLike(String name);
}
