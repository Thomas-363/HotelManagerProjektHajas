package com.ictdemy.springHotelManager.data.entities;


import jakarta.persistence.*;

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
    private double pricePerNight;


    private int numberOfNights;
    private boolean paymentCompleted;
    

}
