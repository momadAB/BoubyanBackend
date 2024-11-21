package com.example.demo.service;

import com.example.demo.bo.*;
import com.example.demo.entity.AccountEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.auth.CustomUserDetailsService;
import com.example.demo.util.Roles;

import com.example.demo.util.Status;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final CustomUserDetailsService userDetailsService;
  private final AccountRepository accountRepository;

  public UserServiceImpl(
          UserRepository userRepository,
          BCryptPasswordEncoder bCryptPasswordEncoder,
          CustomUserDetailsService userDetailsService,
          AccountRepository accountRepository) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.userDetailsService = userDetailsService;
    this.accountRepository = accountRepository;
  }

  @Override
  public OnboardingResponse createUser(CreateUserRequest request) {
    UserEntity userEntity = new UserEntity();

    // Check if there is already a user with the same username
    if (userRepository.existsByUsernameIgnoreCase(request.getUsername())) {
      throw new RuntimeException("Username already exists");
    }

    userEntity.setUsername(request.getUsername());
    userEntity.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
    userEntity.setRole(Roles.user);

    // If my civil ID, make admin
    if(request.getCivilId().equals("300031800574")) {
      userEntity.setRole(Roles.admin);
    }

    // Check that first name and last name are only English letters
    if (!request.getFirstName().matches("^[a-zA-Z]+$") || !request.getLastName().matches("^[a-zA-Z]+$")) {
      throw new RuntimeException("First name and last name must be only English letters");
    }

    userEntity.setFirstName(request.getFirstName());
    userEntity.setLastName(request.getLastName());
    userEntity.setCivilId(request.getCivilId());
    userEntity.setMobileNumber(request.getMobileNumber());
    userEntity.setAccountStatus(Status.ACTIVE);

    // Create a new bank account for the user
    AccountEntity accountEntity = new AccountEntity();
    // Set random account number
    accountEntity.setAccountNumber(String.valueOf((int) (Math.random() * 100000)));
    accountEntity.setBalance(50.0);

    accountEntity = accountRepository.save(accountEntity);

    userEntity.setAccountEntity(accountEntity);

    // Save UserEntity first to make it persistent
    userEntity = userRepository.save(userEntity);



    return new OnboardingResponse("Customer onboarded successfully",
            userEntity.getId(), userEntity.getAccountEntity().getAccountNumber(),
            userEntity.getAccountEntity().getBalance());
  }

  public UserResponse getProfile(String filterBefore, String filterAfter) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();

    CustomUserDetails userDetails = userDetailsService.loadUserByUsername(username);

    // Build the UserResponse with filtered transactions
    UserResponse response = new UserResponse(
            userDetails.getId(),
            userDetails.getFirstName(),
            userDetails.getLastName(),
            userDetails.getUsername(),
            userDetails.getRole().toString(),
            userDetails.getMobileNumber(),
            userDetails.getCivilId());

    return response;
  }

  public void handleFailedLogin(String username) {
    UserEntity userEntity = userRepository.findByUsernameIgnoreCase(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (userEntity.getAccountStatus() == Status.INACTIVE) {
      throw new RuntimeException("Account is locked. Please contact support.");
    }

    userEntity.incrementLoginAttempts();

    if (userEntity.getLoginAttempts() >= 3) {
      userEntity.setAccountStatus(Status.INACTIVE);
      userEntity.setLoginAttempts(0);
    }

    userRepository.save(userEntity);
  }

  public void handleSuccessfulLogin(String username) {
    UserEntity userEntity = userRepository.findByUsernameIgnoreCase(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (userEntity.getAccountStatus() == Status.INACTIVE) {
      throw new RuntimeException("Account is locked. Please contact support.");
    }

    userEntity.resetLoginAttempts();
    userRepository.save(userEntity);
  }

  @Override
  public BalanceResponse getBalanceByUsername(String username) {
    UserEntity userEntity = userRepository.findByUsernameIgnoreCase(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (userEntity.getAccountStatus() == Status.INACTIVE) {
      throw new RuntimeException("Account is locked. Please contact support.");
    }

    return new BalanceResponse(userEntity.getAccountEntity().getBalance());
  }
}
