package com.plotorion.plotorion.controller;


import com.plotorion.plotorion.dto.LoginRequest;
import com.plotorion.plotorion.dto.LoginResponse;
import com.plotorion.plotorion.dto.RefreshTokenRequest;
import com.plotorion.plotorion.dto.RegisterRequest;
import com.plotorion.plotorion.exception.InvalidTokenException;
import com.plotorion.plotorion.service.AuthService;
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
