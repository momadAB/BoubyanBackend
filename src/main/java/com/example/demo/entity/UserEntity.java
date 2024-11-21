package com.example.demo.entity;

import com.example.demo.util.Roles;
import com.example.demo.util.Status;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;

@Entity
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // todo validate name must be english letters only
  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "username", nullable = false, unique = true, length = 15)
  private String username;

  // todo validate password length when input in service
  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "civil_id", nullable = false, unique = true, length = 12)
  private String civilId;

  @Column(name = "mobile_number", nullable = false, unique = true, length = 8)
  private String mobileNumber;

  @Enumerated(EnumType.STRING)
  private Roles role;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "account_entity", referencedColumnName = "id", nullable = false)
  private AccountEntity accountEntity;

  @Enumerated(EnumType.STRING)
  private Status accountStatus;

  private int loginAttempts;

  public void incrementLoginAttempts() {
    this.loginAttempts++;
  }

  public void resetLoginAttempts() {
    this.loginAttempts = 0;
  }

  public int getLoginAttempts() {
    return loginAttempts;
  }

  public void setLoginAttempts(int loginAttempts) {
    this.loginAttempts = loginAttempts;
  }

  public Status getAccountStatus() {
    return accountStatus;
  }

  public void setAccountStatus(Status accountStatus) {
    this.accountStatus = accountStatus;
  }

  public AccountEntity getAccountEntity() {
    return accountEntity;
  }

  public void setAccountEntity(AccountEntity accountEntity) {
    this.accountEntity = accountEntity;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getCivilId() {
    return civilId;
  }

  public void setCivilId(String civilId) {
    this.civilId = civilId;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public Roles getRole() {
    return role;
  }

  public void setRole(Roles role) {
    this.role = role;
  }
}
