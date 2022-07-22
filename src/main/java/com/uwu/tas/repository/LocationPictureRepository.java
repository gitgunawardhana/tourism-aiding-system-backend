package com.uwu.tas.repository;

import com.uwu.tas.entity.LocationPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationPictureRepository extends JpaRepository<LocationPicture, Long> {
}
