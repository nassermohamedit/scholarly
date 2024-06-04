package com.scholarly.dto;

import com.scholarly.entity.Role;

public record Credentials(long id, Role role, String password) {
}