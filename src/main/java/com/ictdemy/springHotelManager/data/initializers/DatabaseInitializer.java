package com.ictdemy.springHotelManager.data.initializers;


import com.ictdemy.springHotelManager.data.entities.CustomerEntity;
import com.ictdemy.springHotelManager.data.entities.PaymentAccountEntity;
import com.ictdemy.springHotelManager.data.entities.RoomEntity;
import com.ictdemy.springHotelManager.data.entities.UserEntity;
import com.ictdemy.springHotelManager.data.repositories.CustomerRepository;
import com.ictdemy.springHotelManager.data.repositories.PaymentAccountRepository;
import com.ictdemy.springHotelManager.data.repositories.RoomRepository;
import com.ictdemy.springHotelManager.data.repositories.UserRepository;
import com.ictdemy.springHotelManager.models.enums.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final PaymentAccountRepository paymentAccountRepository;
    private final PasswordEncoder passwordEncoder;

    public DatabaseInitializer(RoomRepository roomRepository,
                               UserRepository userRepository,
                               CustomerRepository customerRepository, PaymentAccountRepository paymentAccountRepository,
                               PasswordEncoder passwordEncoder) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.paymentAccountRepository = paymentAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("DatabaseInitializer.run");

        if (roomRepository.count() == 0) {
            for (int i = 1; i <= 6; i++) {
                RoomEntity room = new RoomEntity();
                room.setNumber(String.valueOf(i));
                room.setCapacity(2);
                room.setOccupied(0);
                if (i<=3) {
                    room.setPricePerNight(BigDecimal.valueOf(10.0));
                } else room.setPricePerNight(BigDecimal.valueOf(15.00));
                roomRepository.save(room);
            }
        }
        System.out.println("DatabaseInitializer.run - rooms created");


        if (userRepository.findByEmail("admin@hotel.sk").isEmpty()) {
            UserEntity admin = new UserEntity();
            admin.setName("SpringHotelManager");
            admin.setSurname("admin");
            admin.setEmail("admin@hotel.sk");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setUserRole(UserRole.ROLE_ADMIN);
            userRepository.save(admin);
        }

        if (userRepository.findByEmail("emily.brown@hotel.sk").isEmpty()) {
            UserEntity receptionist = new UserEntity();
            receptionist.setName("Emily");
            receptionist.setSurname("Brown");
            receptionist.setEmail("emily.brown@hotel.sk");
            receptionist.setPassword(passwordEncoder.encode("emily123"));
            receptionist.setUserRole(UserRole.ROLE_RECEPTIONIST);
            userRepository.save(receptionist);
        }
        if (userRepository.findByEmail("jack.miller@hotel.sk").isEmpty()) {
            UserEntity manager = new UserEntity();
            manager.setName("Jack");
            manager.setSurname("Miller");
            manager.setEmail("jack.miller@hotel.sk");
            manager.setPassword(passwordEncoder.encode("jack123"));
            manager.setUserRole(UserRole.ROLE_MANAGER);
            userRepository.save(manager);
        }
        System.out.println("DatabaseInitializer.run - users created");

        if (customerRepository.count() == 0) {
            String[] firstName = {
                    "John", "Sarah", "Michael", "Emily",
                    "David", "Sophia"};
            String[] lastName = {
                    "Smith", "Jones", "Williams", "Brown",
                    "Davis", "Miller"};

            for (int i = 0; i < firstName.length; i++) {
                CustomerEntity customer = new CustomerEntity();
                customer.setName(firstName[i]);
                customer.setSurname(lastName[i]);
                customer.setEmail(firstName[i] + "." + lastName[i] + "@hotel.sk");
                String phoneNumber = "4219" + String.valueOf(i + 10000000);
                customer.setMobileNumber(phoneNumber);
                RoomEntity room = roomRepository.findByNumber(String.valueOf(i + 1));
                customer.setRoom(room);
                room.setOccupied(room.getOccupied() + 1);
                PaymentAccountEntity paymentAccountEntity = new PaymentAccountEntity();
                paymentAccountEntity.setCustomer(customer);
                paymentAccountEntity.setRoom(room);
                paymentAccountEntity.setNumberOfNights(2);
                paymentAccountEntity.setPaymentCompleted(false);

                roomRepository.save(room);
                customerRepository.save(customer);
                paymentAccountRepository.save(paymentAccountEntity);
            }
        }


    }
}
