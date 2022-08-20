package com.uwu.tas.repository;

import com.uwu.tas.entity.AccommodationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccommodationTypeRepository extends JpaRepository<AccommodationType, Long> {

    Optional<AccommodationType> findByName(String name);
}
