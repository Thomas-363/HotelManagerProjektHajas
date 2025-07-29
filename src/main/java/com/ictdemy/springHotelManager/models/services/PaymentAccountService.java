package com.ictdemy.springHotelManager.models.services;

import com.ictdemy.springHotelManager.data.entities.CustomerEntity;
import com.ictdemy.springHotelManager.data.entities.PaymentAccountEntity;

public interface PaymentAccountService {
    void createPaymentAccount(CustomerEntity customerEntity);

    void removePaymentAccount();

}
