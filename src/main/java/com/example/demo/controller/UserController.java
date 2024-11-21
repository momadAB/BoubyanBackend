package com.example.demo.controller;

import com.example.demo.bo.*;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/sayHi")
  public String sayHi() {
    return "Hi, you are an authenticated user";
  }

  @GetMapping("/balance")
  public ResponseEntity<BalanceResponse> getBalance() {
    try {
      // Get the authenticated user's username
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String username = authentication.getName();

      // Fetch the user's balance
      BalanceResponse response = userService.getBalanceByUsername(username);

      if (response != null) {
        return ResponseEntity.status(HttpStatus.OK).body(response);
      } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
  }

}
