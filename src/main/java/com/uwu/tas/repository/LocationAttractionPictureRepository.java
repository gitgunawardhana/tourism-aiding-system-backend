package com.uwu.tas.repository;

import com.uwu.tas.entity.LocationAttraction;
import com.uwu.tas.entity.LocationAttractionPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationAttractionPictureRepository extends JpaRepository<LocationAttractionPicture, Long> {

    List<LocationAttractionPicture> findByLocationAttraction(LocationAttraction locationAttraction);
}
