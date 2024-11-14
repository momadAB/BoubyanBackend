package com.example.demo.bo;

public class UserProfileResponse {

    private Long id;

    private String username;

    private String role;

    private String phoneNumber;

    private String address;

    private String email;

    public UserProfileResponse(Long id, String username, String role, String address, String phoneNumber, String email) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//  public String getStatus() {
//    return status;
//  }
//
//  public void setStatus(String status) {
//    this.status = status;
//  }
}
