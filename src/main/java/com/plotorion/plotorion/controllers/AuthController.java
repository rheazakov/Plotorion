package com.plotorion.plotorion.controllers;


import com.plotorion.plotorion.dtos.LoginRequest;
import com.plotorion.plotorion.dtos.LoginResponse;
import com.plotorion.plotorion.dtos.RefreshTokenRequest;
import com.plotorion.plotorion.dtos.RegisterRequest;
import com.plotorion.plotorion.exceptions.InvalidTokenException;
import com.plotorion.plotorion.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SuppressWarnings("unused")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        authService.registerUser(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(
            @Valid @RequestBody RefreshTokenRequest request) {
        LoginResponse response = authService.refreshToken(request);
        if (response == null) {
            throw new InvalidTokenException("Invalid or expired refresh token");
        }
        return ResponseEntity.ok(response);
    }
}
