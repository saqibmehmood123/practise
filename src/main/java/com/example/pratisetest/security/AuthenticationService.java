package com.example.pratisetest.security;


import com.example.pratisetest.payloads.JwtAuthenticationResponse;
import com.example.pratisetest.payloads.SignUpRequest;
import com.example.pratisetest.payloads.SigninRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
