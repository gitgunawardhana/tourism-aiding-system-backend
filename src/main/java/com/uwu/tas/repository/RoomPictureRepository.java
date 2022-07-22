package com.uwu.tas.repository;

import com.uwu.tas.entity.RoomPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomPictureRepository extends JpaRepository<RoomPicture, Long> {
}
