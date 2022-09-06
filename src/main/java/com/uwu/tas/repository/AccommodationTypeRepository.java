package com.uwu.tas.repository;

import com.uwu.tas.entity.AccommodationType;
import com.uwu.tas.enums.VisibilityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccommodationTypeRepository extends JpaRepository<AccommodationType, Long> {

    Optional<AccommodationType> findByName(String name);

    List<AccommodationType> findAllByStatus(VisibilityStatus status);
}
