package com.example.demo.bo;

import com.example.demo.entity.TransactionEntity;

import java.util.List;

public class UserResponse {

  private Long id;

  private String firstName;

  private String lastName;

  private String username;

  private String role;

  private String mobileNumber;

  private String civilId;

  public UserResponse(
          Long id,
          String firstName,
          String lastName,
          String username,
          String role,
          String mobileNumber,
          String civilId
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.role = role;
    this.mobileNumber = mobileNumber;
    this.civilId = civilId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public String getCivilId() {
    return civilId;
  }

  public void setCivilId(String civilId) {
    this.civilId = civilId;
  }
}
