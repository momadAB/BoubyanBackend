package com.example.demo.controller;

import com.example.demo.bo.MessageResponse;
import com.example.demo.bo.ReactivateUserRequest;
import com.example.demo.bo.UserResponse;
import com.example.demo.entity.TransactionEntity;
import com.example.demo.service.AdminService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin-dashboard")
public class AdminController {

  private final AdminService adminService;

  public AdminController(AdminService adminService) {
    this.adminService = adminService;
  }

  // Test
  @GetMapping("/sayHi")
  public String sayHi() {
    return "Hi, you are an authenticated admin";
  }

  @PostMapping("/reactivate")
  public MessageResponse reactivateAccount(@RequestBody ReactivateUserRequest request) {
    return adminService.activateAccount(request.getId());
  }
}
