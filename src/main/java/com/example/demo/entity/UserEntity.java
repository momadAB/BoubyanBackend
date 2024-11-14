package com.example.demo.entity;

import com.example.demo.util.Roles;
import java.util.List;
import javax.persistence.*;

@Entity
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username", nullable = false)
  private String username;

  private String email;
  private String phoneNumber;
  private String address;

  @Column(name = "password", nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  private Roles role;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private BankAccountEntity bankAccount;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TransactionEntity> transactions;

  public List<TransactionEntity> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<TransactionEntity> transactions) {
    this.transactions = transactions;
  }

  public BankAccountEntity getBankAccount() {
    return bankAccount;
  }

  public void setBankAccount(BankAccountEntity bankAccount) {
    this.bankAccount = bankAccount;
  }

  /*
     @OneToOne
     @JoinColumn(name = "role_id")
     private RoleEntity role;

  */

  //    private String username;
  //    private String email;
  //    private String phoneNumber;
  //    private String address;
  //    private String password;
  //    private String role;

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Roles getRole() {
    return role;
  }

  public void setRole(Roles role) {
    this.role = role;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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
  /*
  public RoleEntity getRole() {
      return role;
  }

  public void setRole(RoleEntity role) {
      this.role = role;
  }*/

}
