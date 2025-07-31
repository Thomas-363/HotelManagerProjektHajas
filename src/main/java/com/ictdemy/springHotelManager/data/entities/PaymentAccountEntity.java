package com.ictdemy.springHotelManager.data.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class PaymentAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long paymentAccountId;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @OneToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;


    @Column(nullable = false)
    private int numberOfNights;

    @Column(nullable = false)
    private boolean paymentCompleted;

    @Column (nullable = false)
    private BigDecimal totalPrice;

    public long getPaymentAccountId() {
        return paymentAccountId;
    }

    public void setPaymentAccountId(long paymentAccountId) {
        this.paymentAccountId = paymentAccountId;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }


    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public boolean isPaymentCompleted() {
        return paymentCompleted;
    }

    public void setPaymentCompleted(boolean paymentCompleted) {
        this.paymentCompleted = paymentCompleted;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
