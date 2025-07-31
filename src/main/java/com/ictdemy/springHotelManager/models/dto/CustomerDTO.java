package com.ictdemy.springHotelManager.models.dto;


import jakarta.validation.constraints.*;

public class CustomerDTO {


    private long customerId;

    @NotBlank(message = "Enter customer name")
    private String name;

    @NotBlank(message = "Enter customer surname")
    private String surname;

    @Email(message = "Enter valid email")
    @NotBlank(message = "Enter customer email")
    private String email;

    @Pattern(regexp = "^[+]?[0-9]{1,4}[-\s]?[0-9]{1,12}$", message = "Neplatný formát telefónneho čísla")
    private String mobileNumber;

    private String roomNumber;

    private String previousRoomNumber;

    @NotNull(message = "Enter number of nights")
    @Min(value = 1, message = "At least one night")
    private int numberOfNights;


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

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getPreviousRoomNumber() {
        return previousRoomNumber;
    }

    public void setPreviousRoomNumber(String previousRoomNumber) {
        this.previousRoomNumber = previousRoomNumber;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }
}
