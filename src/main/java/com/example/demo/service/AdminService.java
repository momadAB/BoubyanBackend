package com.example.demo.service;

import com.example.demo.bo.MessageResponse;
import com.example.demo.bo.UserResponse;

import java.util.List;

public interface AdminService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    MessageResponse activateAccount(Long id);
}
