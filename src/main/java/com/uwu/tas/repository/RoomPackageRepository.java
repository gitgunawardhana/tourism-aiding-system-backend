package com.uwu.tas.repository;

import com.uwu.tas.entity.Accommodation;
import com.uwu.tas.entity.RoomPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomPackageRepository extends JpaRepository<RoomPackage, Long> {

    long countByRoom_Accommodation(Accommodation accommodation);
}
