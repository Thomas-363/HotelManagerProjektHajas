package com.ictdemy.springHotelManager.models.services;

import com.ictdemy.springHotelManager.data.entities.CustomerEntity;
import com.ictdemy.springHotelManager.models.dto.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    void accommodate(CustomerEntity customer);
    List<CustomerDTO> getAll();
    void remove(long userId);
    long getCountOfAccommodateCustomers();
    Optional<CustomerDTO> findById(long customerId);
    void update(CustomerEntity customer);
    boolean emailExists(String email);
    Optional<CustomerEntity> findByEmail(String email);

}
