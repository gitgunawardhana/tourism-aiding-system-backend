package com.uwu.tas.repository;

import com.uwu.tas.entity.Location;
import com.uwu.tas.entity.LocationAttraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationAttractionRepository extends JpaRepository<LocationAttraction, Long> {

    List<LocationAttraction> findByLocation(Location location);

    long countByLocation(Location location);

    boolean existsByName(String name);

    Optional<LocationAttraction> findByName(String name);

    @Query(value = "SELECT la from LocationAttraction la " +
            "WHERE la.location=:location AND la.name LIKE %:text%")
    List<LocationAttraction> findByLocationAndNameLike(@Param("location") Location location,
                                                       @Param("text") String text);
}
