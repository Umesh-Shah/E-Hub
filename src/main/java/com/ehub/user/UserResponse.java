package com.ehub.user;

public record UserResponse(Long id, String firstName, String lastName, String email, Role role) {

    static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole());
    }
}
