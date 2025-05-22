package com.ictdemy.springHotelManager.models.services;

import com.ictdemy.springHotelManager.models.dto.CustomerDTO;

public interface AccommodateService {
    boolean accommodateCustomer(CustomerDTO customerDTO);

    boolean editCustomer(CustomerDTO customer);

}
