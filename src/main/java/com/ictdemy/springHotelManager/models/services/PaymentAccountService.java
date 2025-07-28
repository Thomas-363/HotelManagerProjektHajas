package com.ictdemy.springHotelManager.models.services;

import com.ictdemy.springHotelManager.data.entities.PaymentAccountEntity;

public interface PaymentAccountService {
    void createPaymentAccount(PaymentAccountEntity paymentAccount);

    void removePaymentAccount();

}
