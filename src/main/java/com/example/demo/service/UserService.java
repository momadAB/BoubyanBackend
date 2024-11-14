package com.example.demo.service;

import com.example.demo.bo.*;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);
    UserResponse updateProfile(UpdateProfileRequest request);
    UserResponse getProfile();
    public UpdateBalanceResponse updateBalance(UpdateBalanceRequest request);
    //    UserBalanceResponse withdraw(UserTransactionRequest request);
}
