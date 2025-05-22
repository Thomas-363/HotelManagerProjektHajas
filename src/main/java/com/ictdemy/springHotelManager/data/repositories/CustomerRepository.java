package com.ictdemy.springHotelManager.data.repositories;


import com.ictdemy.springHotelManager.data.entities.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByRoom_Number(String number);
    boolean existsByEmail(String email);
    Optional<CustomerEntity> findByEmail(String email);

}
