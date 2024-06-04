package com.scholarly.dto;

public record AuthToken(long id, String username, String role, String token) {
}