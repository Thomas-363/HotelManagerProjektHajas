package com.ictdemy.springHotelManager.models.dto.mappers;


import com.ictdemy.springHotelManager.data.entities.PaymentAccountEntity;
import com.ictdemy.springHotelManager.models.dto.PaymentAccountDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentAccountMapper {

    PaymentAccountDTO toDTO (PaymentAccountEntity source);

    PaymentAccountEntity toEntity (PaymentAccountDTO source);
}
