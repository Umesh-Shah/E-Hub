package com.ehub.user;

public record AuthResponse(String token, long expiresInSeconds, UserResponse user) {
}
