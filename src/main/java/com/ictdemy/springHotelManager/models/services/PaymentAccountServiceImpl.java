package com.ictdemy.springHotelManager.models.services;

import com.ictdemy.springHotelManager.data.entities.CustomerEntity;
import com.ictdemy.springHotelManager.data.entities.PaymentAccountEntity;
import com.ictdemy.springHotelManager.data.repositories.PaymentAccountRepository;
import com.ictdemy.springHotelManager.models.dto.mappers.PaymentAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentAccountServiceImpl implements PaymentAccountService{


    @Autowired
    private PaymentAccountRepository paymentAccountRepository;

    @Autowired
    private PaymentAccountMapper paymentAccountMapper;


    @Override
    public PaymentAccountEntity createPaymentAccount(CustomerEntity customerEntity) {
        PaymentAccountEntity paymentAccountEntity = new PaymentAccountEntity();
        paymentAccountEntity.setCustomer(customerEntity);
        paymentAccountEntity.setRoom(customerEntity.getRoom());
        paymentAccountEntity.setNumberOfNights(2);
        paymentAccountEntity.setPaymentCompleted(false);

        return paymentAccountEntity;
    }

    @Override
    public void removePaymentAccount(CustomerEntity customerEntity) {


    }

    @Override
    public Optional<PaymentAccountEntity> findByCustomer(CustomerEntity customerEntity) {
        return paymentAccountRepository.findById(customerEntity.getCustomerId());
    }

}
