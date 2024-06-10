package com.scholarly.utils;

import com.scholarly.dto.Profile;
import com.scholarly.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserProfileMapper implements DataMapper<User, Profile> {

    @Override
    public Profile map(User data) {
        return new Profile(
                data.getId(),
                data.getUsername(),
                data.getFirstname(),
                data.getLastname(),
                data.getEmail(),
                data.getGender(),
                data.getCountry()
        );
    }
}
