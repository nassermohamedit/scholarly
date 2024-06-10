package com.scholarly.dto;

import com.scholarly.entity.Gender;

public record Profile(
        long id,
        String username,
        String firstname,
        String lastname,
        String email,
        Gender gender,
        String country) {
}
