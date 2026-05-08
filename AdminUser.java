package com.toystore.onlinetoystore.model;

public class AdminUser extends User {

    private String adminLevel;

    public AdminUser() {}

    public AdminUser(String userId, String username, String password,
                     String email, String address, String adminLevel) {
        super(userId, username, password, email, address, "ADMIN");
        this.adminLevel = adminLevel;
    }

    public String getAdminLevel() { return adminLevel; }
    public void setAdminLevel(String adminLevel) { this.adminLevel = adminLevel; }

    public void viewSystemLogs() {
        System.out.println("Admin " + getUsername() + " is viewing system logs.");
    }

    public void manageUsers() {
        System.out.println("Admin " + getUsername() + " is managing users.");
    }
}