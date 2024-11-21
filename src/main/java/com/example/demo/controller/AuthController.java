package com.example.demo.controller;

import com.example.demo.bo.CreateUserRequest;
import com.example.demo.bo.OnboardingResponse;
import com.example.demo.bo.auth.AuthenticationResponse;
import com.example.demo.bo.auth.CreateLoginRequest;
import com.example.demo.service.UserService;
import com.example.demo.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

  @Autowired private UserService userService;

  @Autowired private AuthService authService;

  @PostMapping("/signup")
  public ResponseEntity<OnboardingResponse> createUser(@RequestBody CreateUserRequest request) {
    try {

      OnboardingResponse response = userService.createUser(request);

      // Check if the response is not null (indicating a successful creation)
      if (response != null) {
        // Return a 201 Created status code along with the created user data
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
      } else {
        // Handle the case where the creation was not successful (e.g., validation failed)
        // You can return a different status code or error message as needed
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> login(
      @RequestBody CreateLoginRequest authenticationRequest) {

    try {
      AuthenticationResponse authenticationResponse = authService.login(authenticationRequest);
      HttpStatus status = HttpStatus.OK;

      if (authenticationResponse == null) {
        userService.handleFailedLogin(authenticationRequest.getUsername());
        status = HttpStatus.BAD_REQUEST;
      } else {
        userService.handleSuccessfulLogin(authenticationRequest.getUsername());
      }

      return new ResponseEntity<>(authenticationResponse, status);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
  }
}
