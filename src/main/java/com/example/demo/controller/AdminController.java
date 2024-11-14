package com.example.demo.controller;

import com.example.demo.bo.UserProfileResponse;
import com.example.demo.bo.UserResponse;
import com.example.demo.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin-dashboard")
public class AdminController {


    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/sayHi")
    public String sayHi(){
        return "Hi, you are an authenticated admin";
    }

    @GetMapping("/getAllUsers")
    public List<UserResponse> getAllProfiles() {
        // Fetch all users from the admin service
        return adminService.getAllUsers();
    }
    @GetMapping("/getUser/{id}")
    public UserResponse findUserById(@PathVariable Long id) {
        return adminService.getUserById(id);
    }
    @DeleteMapping ("/deleteUser/{id}")
    public ResponseEntity<UserResponse> deleteUserById(@PathVariable Long id) {
        UserResponse response = (adminService.deleteUserById(id));

        // Check if the response is not null (indicating a successful deletion)
        if (response != null) {
            // Return a 201 Created status code along with some of the deleted user data
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } else {
            // Handle the case where the update was not successful (e.g., delete failed)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
