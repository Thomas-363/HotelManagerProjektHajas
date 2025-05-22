package com.ictdemy.springHotelManager.models.dto.mappers;


import com.ictdemy.springHotelManager.data.entities.RoomEntity;
import com.ictdemy.springHotelManager.models.dto.RoomDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    RoomDTO toDto(RoomEntity room);

    RoomEntity toEntity(RoomDTO dto);

}
