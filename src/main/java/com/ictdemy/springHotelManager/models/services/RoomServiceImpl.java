package com.ictdemy.springHotelManager.models.services;

import com.ictdemy.springHotelManager.data.entities.RoomEntity;
import com.ictdemy.springHotelManager.data.repositories.RoomRepository;
import com.ictdemy.springHotelManager.models.dto.RoomDTO;
import com.ictdemy.springHotelManager.models.dto.mappers.RoomMapper;
import com.ictdemy.springHotelManager.models.exceptions.NoAvailableRoomsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public List<RoomDTO> getAllRooms() {
        return StreamSupport.stream(roomRepository.findAll().spliterator(), false)
                .map(i -> roomMapper.toDto(i))
                .toList();
    }

    @Override
    public RoomEntity findRoomByNumber(String roomNumber) {
        return roomRepository.findByNumber(roomNumber);
    }

    @Override
    public void saveRoom(RoomEntity room) {
        roomRepository.save(room);
    }

    @Override
    public long getCountOfAvailableRooms() {
        return roomRepository.countByOccupied(0);
    }

    //Ziskaj izby kde je volne miesto
    @Override
    public List<RoomEntity> getFreeRooms(int guestCount) {
        List<RoomEntity> freeRooms = roomRepository.findFreeRooms(guestCount);
        if (freeRooms.isEmpty()) {
            throw new NoAvailableRoomsException();
        }
        return freeRooms;
    }

    //Ziskaj izbky kde je volne miesto spolu s aktualnou izbou hosta
    @Override
    public List<RoomEntity> getFreeRoomsIncludingCurrent(int guestCount, String currentRoomNumber) {
        List<RoomEntity> freeRooms = roomRepository.findFreeRooms(guestCount);
        if (freeRooms.isEmpty()) {
            throw new NoAvailableRoomsException();
        }
        // Získaj izbu, kde je zákazník momentálne ubytovaný
        RoomEntity currentRoom = findRoomByNumber(currentRoomNumber);

        // Ak nie je medzi voľnými izbami, pridaj ju tam dočasne
        if (currentRoom != null && (!freeRooms.contains(currentRoom))) {
            currentRoom.setOccupied(currentRoom.getOccupied() - 1);
            freeRooms.add(currentRoom);
        } else if (freeRooms.contains(currentRoom)) {
            int index = freeRooms.indexOf(currentRoom);
            freeRooms.get(index).setOccupied(freeRooms.get(index).getOccupied() - 1);
        }

        return freeRooms;
    }


}
