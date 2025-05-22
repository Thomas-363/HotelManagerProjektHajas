package com.ictdemy.springHotelManager.models.dto;


import com.ictdemy.springHotelManager.models.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class UserDTO {

    private long userId;

    @NotBlank(message = "Enter user name")
    private String name;

    @NotBlank(message = "Enter user surname")
    private String surname;

    @Email(message = "Enter valid email")
    @NotBlank(message = "Enter user email")
    private String email;

    @NotBlank(message = "Enter user password")
    @Size(min = 6, message = "Password must have at least 6 characters")
    private String password;

    @NotBlank(message = "Enter user password")
    @Size(min = 6, message = "Password must have at least 6 characters")
    private String confirmPassword;

    @NotNull(message = "Role cannot be null")
    private UserRole userRole;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}

