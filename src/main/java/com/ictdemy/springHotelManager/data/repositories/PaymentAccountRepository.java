package com.ictdemy.springHotelManager.data.repositories;

import com.ictdemy.springHotelManager.data.entities.PaymentAccountEntity;
import org.springframework.data.repository.CrudRepository;

public interface PaymentAccountRepository extends CrudRepository<PaymentAccountEntity, Long> {

}
