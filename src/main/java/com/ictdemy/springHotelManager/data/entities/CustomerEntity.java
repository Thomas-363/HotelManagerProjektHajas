package com.ictdemy.springHotelManager.data.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import com.ictdemy.springHotelManager.data.entities.RoomEntity;


@Entity
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String mobileNumber;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    @OneToOne
    @JoinColumn(name = "payment_account_id")
    PaymentAccountEntity paymentAccountEntity;


    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }
}
