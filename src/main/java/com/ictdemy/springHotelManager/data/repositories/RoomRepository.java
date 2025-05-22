package com.ictdemy.springHotelManager.data.repositories;


import com.ictdemy.springHotelManager.data.entities.RoomEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends CrudRepository<RoomEntity, Long> {

    RoomEntity findByNumber(String number);

    long countByOccupied(int getOccupied);

    @Query("SELECT r FROM RoomEntity r WHERE (r.capacity - r.occupied) >= :guestCount")
    List<RoomEntity> findFreeRooms(@Param("guestCount") int guestCount);
}
