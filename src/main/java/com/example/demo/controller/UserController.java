package com.example.demo.controller;

import com.example.demo.bo.UpdateBalanceRequest;
import com.example.demo.bo.UpdateBalanceResponse;
import com.example.demo.bo.UpdateProfileRequest;
import com.example.demo.bo.UserResponse;
import com.example.demo.service.UserService;
import com.example.demo.service.auth.CustomUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {

    private final UserService userService;
    private final CustomUserDetailsService userDetailsService;

    public UserController(UserService userService, CustomUserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/sayHi")
    public String sayHi(){
        return "Hi, you are an authenticated user";
    }

    @PutMapping("/update-profile")
    ResponseEntity<UserResponse> updateProfile(@RequestBody UpdateProfileRequest request){
        UserResponse response = userService.updateProfile(request);

        // Check if the response is not null (indicating a successful update)
        if (response != null) {
            // Return a 201 Created status code along with some of the updated user data
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            // Handle the case where the update was not successful (e.g., validation failed)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/profile")
    ResponseEntity<UserResponse> getProfile() {
        UserResponse response = userService.getProfile();

        // Check if the response is not null (indicating a successful get)
        if (response != null) {
            // Return a 201 Created status code along with some of the user data
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            // Handle the case where the get was not successful (e.g., validation failed)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
