package com.example.demo.service;

import com.example.demo.bo.*;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);
    UserProfileResponse updateProfile(UpdateProfileRequest request);
    UserProfileResponse getProfile();
//    UserBalanceResponse withdraw(UserTransactionRequest request);
}
