package com.example.demo.bo;

import com.example.demo.entity.BankAccountEntity;
import com.example.demo.entity.TransactionEntity;
import java.util.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

  private Long id;
  private String userName;
  private String password;
  private String email;
  private String address;
  private String phone;
  private String role;
  private List<TransactionEntity> transactions;
  private BankAccountEntity bankAccount;

  public BankAccountEntity getBankAccount() {
    return bankAccount;
  }

  public void setBankAccount(BankAccountEntity bankAccount) {
    this.bankAccount = bankAccount;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public List<TransactionEntity> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<TransactionEntity> transactions) {
    this.transactions = transactions;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhoneNumber() {
    return phone;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singleton(new SimpleGrantedAuthority(role));
  }

  @Override
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String getUsername() {
    return userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public Map<String, Object> getClaims() {
    HashMap<String, Object> claims = new HashMap<>();

    claims.put("id", this.id);
    claims.put("userName", this.userName);
    claims.put("role", role);

    return claims;
  }
}
