package com.uwu.tas.repository;

import com.uwu.tas.entity.LocationPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationPictureRepository extends JpaRepository<LocationPicture, Long> {
    @Query(value = "SELECT * FROM location_picture WHERE location_id = ?1 LIMIT 1", nativeQuery = true)
    Optional<LocationPicture> getFirstLocationImageByLocationId(long locationId);
}
