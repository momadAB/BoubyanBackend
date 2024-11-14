package com.example.demo.service.auth;

import com.example.demo.bo.auth.AuthenticationResponse;
import com.example.demo.bo.auth.CreateLoginRequest;
import com.example.demo.bo.auth.LogoutResponse;
import com.example.demo.bo.CustomUserDetails;
import com.example.demo.config.JWTUtil;
import com.example.demo.exception.BodyGuardException;
import com.example.demo.exception.UserNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService userDetailsService;

    private final JWTUtil jwtUtil;

    public AuthServiceImpl(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    /*
    - It checks that the **`username`** and **`password`** in the **`authenticationRequest`** are not null or empty using the **`requiredNonNull`** method.
    - It converts the **`username`** to lowercase (standardizing it).
    - It calls the **`authenticate`** method to perform user authentication using Spring Security's **`AuthenticationManager`**.
    - It loads user details using the **`userDetailsService`**.
    - It generates an access token using the **`jwtUtil`**.
    - Finally, it constructs an **`AuthenticationResponse`** object with the user details and access token and returns it.
     */
    @Override
    public AuthenticationResponse login(CreateLoginRequest authenticationRequest) {
        requiredNonNull(authenticationRequest.getUsername(), "username");
        requiredNonNull(authenticationRequest.getPassword(), "password");

        String username = authenticationRequest.getUsername().toLowerCase();
        String password = authenticationRequest.getPassword();

        authenticate(username, password);

        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String accessToken = jwtUtil.generateToken(userDetails);

        AuthenticationResponse response = new AuthenticationResponse();
        response.setId(userDetails.getId());
        response.setUsername(userDetails.getUsername());
        response.setRole(userDetails.getRole());
        response.setToken("Bearer " + accessToken);

        return response;
    }




    @Override
    public void logout(LogoutResponse logoutResponse) {
        requiredNonNull(logoutResponse.getToken(),"token");
    }

    private void requiredNonNull(Object obj, String name) {
        if (obj == null || obj.toString().isEmpty()){
            throw new BodyGuardException(name + " can't be empty");
        }
    }

    private void authenticate(String username, String password){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        }catch (BadCredentialsException e){
            throw  new BadCredentialsException("Incorrect password");
        }catch (AuthenticationServiceException e){
            throw  new UserNotFoundException("Incorrect username");
        }
    }
}
