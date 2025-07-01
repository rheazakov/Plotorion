package com.plotorion.plotorion.utils;


import com.plotorion.plotorion.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;
import java.util.UUID;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CurrentUser {
    private static final String ANONYMOUS_USER = "anonymousUser";

    private final UUID userId;
    private final String username;
    private final boolean authenticated;

    public CurrentUser(){
        UserInfo userInfo = extractUserInfo();
        this.userId = userInfo.userId();
        this.username = userInfo.username();
        this.authenticated = userInfo.authenticated();
    }
    private UserInfo extractUserInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null
                && auth.isAuthenticated()
                && auth.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
            return new UserInfo(userDetails.getUser().getId(), userDetails.getUser().getUsername(), true);
        }
        return UserInfo.unauthenticated();
    }
    public Optional<UUID> getUserId() {
        return Optional.ofNullable(userId);
    }

    public Optional<String> getUsername() {
        return Optional.ofNullable(username);
    }

}
