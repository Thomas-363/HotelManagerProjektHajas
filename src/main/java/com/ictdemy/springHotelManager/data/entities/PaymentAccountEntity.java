package com.ictdemy.springHotelManager.data.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class PaymentAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long paymentAccountId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private long customerId;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private long roomId;

    @OneToOne
    @JoinColumn(name = "price_per_night")
    private BigDecimal pricePerNight;

    @Column(nullable = false)
    private int numberOfNights;

    @Column(nullable = false)
    private boolean paymentCompleted;

    public long getPaymentAccountId() {
        return paymentAccountId;
    }

    public void setPaymentAccountId(long paymentAccountId) {
        this.paymentAccountId = paymentAccountId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
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
}
