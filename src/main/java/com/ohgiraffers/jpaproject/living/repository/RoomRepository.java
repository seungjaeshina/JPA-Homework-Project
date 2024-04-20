package com.ohgiraffers.jpaproject.living.repository;

import com.ohgiraffers.jpaproject.living.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query(
            value = "SELECT room_code, total_room FROM tbl_room r ORDER BY room_code",
            nativeQuery = true
    )
    List<Room> findAllRoom();
}
