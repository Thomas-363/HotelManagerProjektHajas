package com.ictdemy.springHotelManager.models.dto.mappers;


import com.ictdemy.springHotelManager.data.entities.UserEntity;
import com.ictdemy.springHotelManager.models.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    UserDTO toDto(UserEntity user);

    @Mapping(target = "password", ignore = true)  // heslo NEPREPÍSAŤ pri update entity
    @Mapping(target = "userId", ignore = true)
        // userId NEPREPÍSAŤ pri update entity
    void updateEntityFromDTO(UserDTO dto, @MappingTarget UserEntity entity);


    UserEntity toEntity(UserDTO user);
}
