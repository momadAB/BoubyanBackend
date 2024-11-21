package com.example.demo.service;

import com.example.demo.bo.MessageResponse;
import com.example.demo.bo.UserResponse;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.auth.CustomUserDetailsService;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.util.Status;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final CustomUserDetailsService userDetailsService;

  public AdminServiceImpl(
      UserRepository userRepository,
      BCryptPasswordEncoder bCryptPasswordEncoder,
      CustomUserDetailsService userDetailsService) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.userDetailsService = userDetailsService;
  }

  @Override
  public MessageResponse activateAccount(Long id) {
    UserEntity user = userRepository.getById(id);
    user.setAccountStatus(Status.ACTIVE);
    userRepository.save(user);
    return new MessageResponse(user.getFirstName() + " account has been reactivated");
  }


  public List<UserResponse> getAllUsers() {
    List<UserEntity> users = userRepository.findAll();

    // Map each UserEntity to UserProfileResponse and return as a list
    return users.stream()
        .map(
            user ->
    new UserResponse(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getUsername(),
            user.getRole().toString(),
            user.getMobileNumber(),
            user.getCivilId()))
        .collect(Collectors.toList());
  }

  public UserResponse getUserById(Long id) {
    UserEntity user = userRepository.getById(id);
    return new UserResponse(
        user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getUsername(),
            user.getRole().toString(),
            user.getMobileNumber(),
            user.getCivilId());
  }


}
