package com.uwu.tas.repository;

import com.uwu.tas.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    boolean existsByName(String name);

    Optional<Location> findByName(String name);

    @Query(value = "SELECT l FROM Location l WHERE l.name LIKE %:text%")
    List<Location> findByNameLike(@Param("text") String text);
}
