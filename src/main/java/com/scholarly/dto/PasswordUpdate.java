package com.scholarly.dto;

public record PasswordUpdate(
        String oldPassword,
        String newPassword
) {
}
