package com.ictdemy.springHotelManager.models.services;

import com.ictdemy.springHotelManager.data.entities.CustomerEntity;
import com.ictdemy.springHotelManager.data.entities.PaymentAccountEntity;

import java.util.Optional;

public interface PaymentAccountService {
    PaymentAccountEntity createPaymentAccount(CustomerEntity customerEntity);

    void removePaymentAccount(CustomerEntity customerEntity);

    Optional<PaymentAccountEntity> findByCustomer(CustomerEntity customerEntity);

}
