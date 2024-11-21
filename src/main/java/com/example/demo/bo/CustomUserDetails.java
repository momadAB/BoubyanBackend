package com.example.demo.bo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class CustomUserDetails implements UserDetails {

  private Long id;
  private String firstName;
  private String lastName;
  private String username;
  private String password;
  private String civilId;
  private String mobileNumber;
  private String role;
  // Getters and setters for new fields
  public String getFirstName() {
    return firstName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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
    claims.put("firstName", this.firstName);
    claims.put("lastName", this.lastName);
    claims.put("userName", this.username);
    claims.put("role", role);
    claims.put("civilId", civilId);
    claims.put("mobileNumber", mobileNumber);
    return claims;
  }
}
