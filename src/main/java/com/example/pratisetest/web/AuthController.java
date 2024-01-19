package com.example.pratisetest.web;

import com.example.pratisetest.payloads.JwtAuthenticationResponse;
import com.example.pratisetest.security.AuthenticationService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import java.util.List;
import java.util.stream.Collectors;
import com.example.pratisetest.payloads.SigninRequest;
import com.example.pratisetest.payloads.SignUpRequest;
@RestController
@RequestMapping("/api/v1/auth")
///@RequiredArgsConstructor
public class AuthController
{
    @Autowired
    private  AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {

        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

}
