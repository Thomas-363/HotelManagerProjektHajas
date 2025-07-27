package com.ictdemy.springHotelManager.models.dto;

import java.math.BigDecimal;

public class PaymentAccountDTO {

    private long paymentAccountId;
    private long customerId;
    private long roomId;
    private BigDecimal pricePerNight;
    private int numberOfNights;
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
