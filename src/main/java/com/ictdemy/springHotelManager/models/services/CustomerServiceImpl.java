package com.ictdemy.springHotelManager.models.services;


import com.ictdemy.springHotelManager.data.entities.CustomerEntity;
import com.ictdemy.springHotelManager.data.repositories.CustomerRepository;
import com.ictdemy.springHotelManager.models.dto.CustomerDTO;
import com.ictdemy.springHotelManager.models.dto.mappers.CustomerMapper;
import com.ictdemy.springHotelManager.models.exceptions.DuplicateEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;


    @Override
    public void accommodate(CustomerEntity customer) {
        customerRepository.save(customer);
    }

    @Override
    public List<CustomerDTO> getAll() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false)
                .map(customerMapper::toDTO)
                .sorted(Comparator.comparing(CustomerDTO::getRoomNumber))
                .toList();
    }

    @Override
    public void remove(long customerId) {
        customerRepository.deleteById(customerId);
    }


    @Override
    public long getCountOfAccommodateCustomers() {
        return customerRepository.count();
    }

    @Override
    public Optional<CustomerDTO> findById(long customerId) {
        return customerRepository.findById(customerId).map(customerMapper::toDTO);
    }

    @Override
    public boolean emailExists(String email) {
        return customerRepository.existsByEmail(email);
    }

    @Override
    public Optional<CustomerEntity> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public void update(CustomerEntity customer) {
        customerRepository.save(customer);
    }


}
