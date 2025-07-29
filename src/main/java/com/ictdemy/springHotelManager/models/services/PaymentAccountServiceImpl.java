package com.ictdemy.springHotelManager.models.services;

import com.ictdemy.springHotelManager.data.entities.CustomerEntity;
import com.ictdemy.springHotelManager.data.entities.PaymentAccountEntity;

public class PaymentAccountServiceImpl implements PaymentAccountService{



    @Override
    public void createPaymentAccount(CustomerEntity customerEntity) {
        PaymentAccountEntity paymentAccountEntity = new PaymentAccountEntity();
        paymentAccountEntity.setCustomer(customerEntity);
        paymentAccountEntity.setRoom(customerEntity.getRoom());
        paymentAccountEntity.setNumberOfNights(2);
        paymentAccountEntity.setPaymentCompleted(false);
    }

    @Override
    public void removePaymentAccount() {

    }
}
