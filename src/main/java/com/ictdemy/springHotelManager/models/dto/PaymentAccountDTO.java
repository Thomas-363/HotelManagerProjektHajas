package com.ictdemy.springHotelManager.models.dto;

import java.math.BigDecimal;

public class PaymentAccountDTO {

    private long paymentAccountId;
    private long customerId;
    private long roomId;
    private BigDecimal pricePerNight;
    private int numberOfNights;
    private boolean paymentCompleted;



}
