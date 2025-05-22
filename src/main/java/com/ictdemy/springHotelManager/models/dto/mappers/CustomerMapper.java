package com.ictdemy.springHotelManager.models.dto.mappers;


import com.ictdemy.springHotelManager.data.entities.CustomerEntity;
import com.ictdemy.springHotelManager.models.dto.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerEntity toEntity(CustomerDTO source);

    @Mapping(source = "customerId", target = "customerId")
    @Mapping(source = "room.number", target = "roomNumber")
    CustomerDTO toDTO(CustomerEntity source);

    @Mapping(target = "customerId", ignore = true)
    void updateCustomerEntity(CustomerDTO source, @MappingTarget CustomerEntity target);


}
