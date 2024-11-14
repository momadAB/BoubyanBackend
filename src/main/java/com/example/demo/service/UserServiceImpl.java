package com.example.demo.service;

import com.example.demo.bo.*;
import com.example.demo.entity.BankAccountEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.BankRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.auth.CustomUserDetailsService;
import com.example.demo.util.Roles;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final CustomUserDetailsService userDetailsService;
  private final BankRepository bankRepository;

  public UserServiceImpl(
      UserRepository userRepository,
      BCryptPasswordEncoder bCryptPasswordEncoder,
      CustomUserDetailsService userDetailsService,
      BankRepository bankRepository) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.userDetailsService = userDetailsService;
    this.bankRepository = bankRepository;
  }

  @Override
  public UserResponse createUser(CreateUserRequest request) {
    UserEntity userEntity = new UserEntity();

    userEntity.setUsername(request.getUsername());
    userEntity.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
    userEntity.setRole(Roles.valueOf(request.getRole()));
    userEntity.setAddress((request.getAddress()));
    userEntity.setPhoneNumber(request.getPhoneNumber());
    userEntity.setEmail(request.getEmail());

    // Save UserEntity first to make it persistent
    userEntity = userRepository.save(userEntity);

    // Create and associate the BankAccountEntity
    BankAccountEntity bankAccount = new BankAccountEntity();
    bankAccount.setUser(userEntity);  // Set the user reference in BankAccountEntity
    bankAccount.setBalance(0.0);

    // Save the BankAccountEntity
    bankAccount = bankRepository.save(bankAccount);

    // Update the userEntity with the bank account association and save it again
    userEntity.setBankAccount(bankAccount);
    userEntity = userRepository.save(userEntity);

    UserResponse response =
        new UserResponse(
            userEntity.getId(),
            userEntity.getUsername(),
            userEntity.getRole().toString(),
            userEntity.getAddress(),
            userEntity.getPhoneNumber(),
            userEntity.getEmail(),
            bankAccount.getBalance(),
            bankAccount.getId());
    return response;
  }

  public UserProfileResponse updateProfile(UpdateProfileRequest request) {
    CustomUserDetails user = userDetailsService.loadUserByUsername(request.getUsername());

    UserEntity userEntity = new UserEntity();
    userEntity.setUsername(request.getUsername());
    userEntity.setAddress(request.getAddress());
    userEntity.setEmail(request.getEmail());
    userEntity.setRole(Roles.valueOf(request.getRole()));
    userEntity.setPhoneNumber(request.getPhoneNumber());
    userEntity.setId(user.getId());
    userEntity.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

    userEntity = userRepository.save(userEntity);
    UserProfileResponse response =
            new UserProfileResponse(
                    userEntity.getId(),
                    userEntity.getUsername(),
                    userEntity.getRole().toString(),
                    userEntity.getAddress(),
                    userEntity.getPhoneNumber(),
                    userEntity.getEmail());
    return response;
  }

  public UserProfileResponse getProfile() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();

    CustomUserDetails userDetails = userDetailsService.loadUserByUsername(username);

    UserProfileResponse response =
        new UserProfileResponse(
            userDetails.getId(),
            userDetails.getUsername(),
            userDetails.getRole(),
            userDetails.getAddress(),
            userDetails.getPhoneNumber(),
            userDetails.getEmail());

    return response;
  }
}
