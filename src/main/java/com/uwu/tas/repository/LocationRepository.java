package com.uwu.tas.repository;

import com.uwu.tas.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    boolean existsByName(String name);

    List<Location> findByNameLike(String name);
}
