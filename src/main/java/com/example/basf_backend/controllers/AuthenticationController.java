package com.example.basf_backend.controllers;

import com.example.basf_backend.configuration.JwtService;
import com.example.basf_backend.models.AuthRequest;
import com.example.basf_backend.models.TokenResponse;
import com.example.basf_backend.models.entities.UserInfo;
import com.example.basf_backend.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserInfoService userInfoService;

    // Sign a new user
    @PostMapping("/signup")
    public UserInfo addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }

    //Get a token for a registered user
    @PostMapping("/login")
    public TokenResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return new TokenResponse(jwtService.generateToken(authRequest.getUsername()));
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}