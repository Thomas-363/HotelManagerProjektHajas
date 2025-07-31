package com.ictdemy.springHotelManager.models.services;

import com.ictdemy.springHotelManager.data.entities.CustomerEntity;
import com.ictdemy.springHotelManager.data.entities.PaymentAccountEntity;
import com.ictdemy.springHotelManager.data.entities.RoomEntity;
import com.ictdemy.springHotelManager.models.dto.CustomerDTO;
import com.ictdemy.springHotelManager.models.dto.mappers.CustomerMapper;
import com.ictdemy.springHotelManager.models.dto.mappers.PaymentAccountMapper;
import com.ictdemy.springHotelManager.models.exceptions.DuplicateEmailException;
import com.ictdemy.springHotelManager.models.exceptions.PasswordsDoNotEqualException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccommodateServiceImpl implements AccommodateService {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private RoomService roomService;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    PaymentAccountService paymentAccountService;

    @Autowired
    PaymentAccountMapper paymentAccountMapper;

    @Override
    public boolean accommodateCustomer(CustomerDTO customerDTO) {


        RoomEntity room = roomService.findRoomByNumber(customerDTO.getRoomNumber());
        PaymentAccountEntity paymentAccountEntity = new PaymentAccountEntity();

        if (room == null) {
            return false;
        }
        CustomerEntity customerEntity = customerMapper.toEntity(customerDTO);
        customerEntity.setRoom(room);
        if (customerService.emailExists(customerDTO.getEmail())) {
            throw new DuplicateEmailException();
        }
        paymentAccountEntity = paymentAccountService.createPaymentAccount(customerEntity);
        paymentAccountEntity.setNumberOfNights(customerDTO.getNumberOfNights());
        paymentAccountEntity.setTotalPrice(paymentAccountService.updateTotalPrice(paymentAccountEntity));

        customerEntity.setPaymentAccountEntity(paymentAccountEntity);
        customerService.accommodate(customerEntity);
        room.setOccupied(room.getOccupied() + 1);
        roomService.saveRoom(room);


        return true;
    }

    @Override
    public boolean editCustomer(CustomerDTO customer) {
        RoomEntity room = roomService.findRoomByNumber(customer.getRoomNumber());
        RoomEntity previousRoom = roomService.findRoomByNumber(customer.getPreviousRoomNumber());
        if (room == null || previousRoom == null) {
            return false;
        }

        //Overenie duplicity emailu
        Optional<CustomerEntity> existingCustomer = customerService.findByEmail(customer.getEmail());
        if (existingCustomer.isPresent()) {
            Long existingId = existingCustomer.get().getCustomerId();
            Long currentId = customer.getCustomerId();

            if (existingId != null && !existingId.equals(currentId)) {
                throw new DuplicateEmailException();
            }
        }


        Optional<CustomerEntity> fetchedCustomer = customerService.findById(customer.getCustomerId())
                .map(customerMapper::toEntity);

        if (fetchedCustomer.isPresent()) {
            CustomerEntity customerEntity = fetchedCustomer.get();
            customerMapper.updateCustomerEntity(customer, customerEntity);
            customerEntity.setRoom(room);
            customerService.update(customerEntity);
            if (!room.getNumber().equals(previousRoom.getNumber())) {
                room.setOccupied(room.getOccupied() + 1);
                roomService.saveRoom(room);

                previousRoom.setOccupied(previousRoom.getOccupied() - 1);
                roomService.saveRoom(previousRoom);
            }
            return true;
        } else {
            return false;
        }


    }
}
