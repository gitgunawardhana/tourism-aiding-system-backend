package com.uwu.tas.repository;

import com.uwu.tas.entity.Accommodation;
import com.uwu.tas.entity.RoomPackage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomPackageRepository extends JpaRepository<RoomPackage, Long> {

    long countByRoom_Accommodation(Accommodation accommodation);

    @Query(value = "SELECT rp FROM RoomPackage rp " +
            "JOIN rp.room r " +
            "JOIN r.accommodation a " +
            "WHERE a=:accommodation " +
            "ORDER BY rp.price ASC ")
    List<RoomPackage> searchAvailablePackageByAccommodation(@Param("accommodation") Accommodation accommodation);
}
