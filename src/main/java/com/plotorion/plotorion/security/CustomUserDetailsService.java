package com.plotorion.plotorion.security;


import com.plotorion.plotorion.model.User;
import com.plotorion.plotorion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String input = username.trim().toLowerCase();
        User user = userRepository.findByUsername(input)
                .orElseThrow(() -> new UsernameNotFoundException("Incorrect username or password."));
        return new CustomUserDetails(user);
    }
}
