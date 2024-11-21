package com.example.demo.service;

import com.example.demo.bo.*;

public interface UserService {
    OnboardingResponse createUser(CreateUserRequest request);
    // UserResponse updateProfile(UpdateProfileRequest request);
    UserResponse getProfile(String filterBefore, String filterAfter);
    void handleFailedLogin(String username);
    void handleSuccessfulLogin(String username);
    BalanceResponse getBalanceByUsername(String username);
    TransferResponse transfer(String username, TransferRequest request);
    //    UserBalanceResponse withdraw(UserTransactionRequest request);
}
