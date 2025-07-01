package com.plotorion.plotorion.utils;

import java.util.UUID;

public record UserInfo(UUID userId, String username, boolean authenticated) {
    static UserInfo unauthenticated() {
        return new UserInfo(null, null, false);
    }
}
