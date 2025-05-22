package com.ictdemy.springHotelManager.models.services;


import com.ictdemy.springHotelManager.data.entities.RoomEntity;
import com.ictdemy.springHotelManager.models.dto.RoomDTO;

import java.util.List;

public interface RoomService {
    List<RoomDTO> getAllRooms();

    RoomEntity findRoomByNumber(String roomNumber);

    void saveRoom(RoomEntity room);

    long getCountOfAvailableRooms();

    List<RoomEntity> getFreeRooms(int guestCount);

    List<RoomEntity> getFreeRoomsIncludingCurrent(int guestCount, String currentRoomNumber);


}
