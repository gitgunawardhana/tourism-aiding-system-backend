package com.uwu.tas.repository;

import com.uwu.tas.entity.AccommodationPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationPictureRepository extends JpaRepository<AccommodationPicture, Long> {
}
