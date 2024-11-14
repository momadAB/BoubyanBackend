package com.example.demo.service;

import com.example.demo.bo.UserResponse;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.BankRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.auth.CustomUserDetailsService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final CustomUserDetailsService userDetailsService;
  private final BankRepository bankRepository;

  public AdminServiceImpl(
      UserRepository userRepository,
      BCryptPasswordEncoder bCryptPasswordEncoder,
      CustomUserDetailsService userDetailsService,
      BankRepository bankRepository) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.userDetailsService = userDetailsService;
    this.bankRepository = bankRepository;
  }

  public List<UserResponse> getAllUsers() {
    List<UserEntity> users = userRepository.findAll();

    // Map each UserEntity to UserProfileResponse and return as a list
    return users.stream()
        .map(
            user ->
                new UserResponse(
                    user.getId(),
                    user.getUsername(),
                    user.getRole().toString(),
                    user.getEmail(),
                    user.getPhoneNumber(),
                    user.getAddress(),
                    user.getBankAccount().getBalance(),
                    user.getBankAccount().getId(),
                    user.getTransactions()))
        .collect(Collectors.toList());
  }

  public UserResponse getUserById(Long id) {
    UserEntity user = userRepository.getById(id);
    return new UserResponse(
        user.getId(),
        user.getUsername(),
        user.getRole().toString(),
        user.getEmail(),
        user.getPhoneNumber(),
        user.getAddress(),
        user.getBankAccount().getBalance(),
        user.getBankAccount().getId(),
            user.getTransactions());
  }

  @Override
  public UserResponse deleteUserById(Long id) {
    UserResponse user = getUserById(id);

    userRepository.deleteById(id);
    return user;
  }
}
