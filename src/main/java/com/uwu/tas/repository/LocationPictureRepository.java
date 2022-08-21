package com.uwu.tas.repository;

import com.uwu.tas.entity.LocationPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationPictureRepository extends JpaRepository<LocationPicture, Long> {

    @Query(value = "SELECT * FROM location_picture WHERE location_id = ?1 ORDER BY id DESC LIMIT 3", nativeQuery = true)
    List<LocationPicture> getLocationImagesByLocationId(long locationId);
}
