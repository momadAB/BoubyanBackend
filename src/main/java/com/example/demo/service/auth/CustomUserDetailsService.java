package com.example.demo.service.auth;

import com.example.demo.bo.CustomUserDetails;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return buildCustomUserDetailsOfUsername(username);
  }

  private CustomUserDetails buildCustomUserDetailsOfUsername(String username) {
    UserEntity user =
        userRepository
            .findByUsernameIgnoreCase(username)
            .orElseThrow(() -> new UserNotFoundException("Incorrect Username"));

    CustomUserDetails userDetails = new CustomUserDetails();
    userDetails.setId(user.getId());
    userDetails.setUsername(user.getUsername());
    userDetails.setPassword(user.getPassword());
    userDetails.setRole(user.getRole().toString());
    userDetails.setFirstName(user.getFirstName());
    userDetails.setLastName(user.getLastName());
    userDetails.setCivilId(user.getCivilId());
    userDetails.setMobileNumber(user.getMobileNumber());

    return userDetails;
  }
}
