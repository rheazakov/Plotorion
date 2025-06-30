package com.plotorion.plotorion.service;

import com.plotorion.plotorion.dto.LoginRequest;
import com.plotorion.plotorion.dto.LoginResponse;
import com.plotorion.plotorion.dto.RefreshTokenRequest;
import com.plotorion.plotorion.dto.RegisterRequest;
import com.plotorion.plotorion.exception.InvalidCredentialsException;
import com.plotorion.plotorion.exception.InvalidTokenException;
import com.plotorion.plotorion.exception.UserAlreadyExistsException;
import com.plotorion.plotorion.model.User;
import com.plotorion.plotorion.model.enums.Role;
import com.plotorion.plotorion.repository.UserRepository;
import com.plotorion.plotorion.security.CustomUserDetails;
import com.plotorion.plotorion.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public void registerUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already in use");
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("Username already taken");
        }

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(Role.ROLE_USER);
        newUser.setName(request.getName());

        userRepository.save(newUser);
    }

    public LoginResponse login(LoginRequest request){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername().trim(),
                            request.getPassword()
                    )
            );

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            String accessToken = jwtUtils.generateAccessToken(userDetails.getUsername(), userDetails.getUser());
            String refreshToken = jwtUtils.generateRefreshToken(userDetails.getUsername());


            return new LoginResponse(accessToken,refreshToken);

        } catch (AuthenticationException e) {
            throw new InvalidCredentialsException("Invalid username/email or password");
        }
    }

    public LoginResponse refreshToken(RefreshTokenRequest request) {
        String username = jwtUtils.extractUsername(request.getRefreshToken());
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!jwtUtils.validateToken(request.getRefreshToken())) {
            throw new InvalidTokenException("Refresh token is invalid or expired");
        }

        String newAccessToken = jwtUtils.generateAccessToken(user.getUsername(), user);

        return new LoginResponse(newAccessToken, request.getRefreshToken());
    }
}
