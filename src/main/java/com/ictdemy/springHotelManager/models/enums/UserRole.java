package com.ictdemy.springHotelManager.models.enums;

public enum UserRole {
    ROLE_MANAGER("Manager"),
    ROLE_ADMIN("Admin"),
    ROLE_RECEPTIONIST("Receptionist");


    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
