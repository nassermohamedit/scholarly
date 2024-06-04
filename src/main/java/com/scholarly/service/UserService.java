package com.scholarly.service;

import com.scholarly.dto.Profile;
import com.scholarly.entity.Role;
import com.scholarly.entity.User;
import com.scholarly.exceptions.NotAllowedException;
import com.scholarly.exceptions.ResourceNotFoundException;
import com.scholarly.repository.RoleRepository;
import com.scholarly.repository.UserRepository;
import com.scholarly.security.AuthenticationService;
import com.scholarly.utils.UserProfileMapper;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserProfileMapper userProfileMapper;

    private final RoleRepository roleRepository;

    private final AuthenticationService authService;

    public Profile getProfileById(long id) {
        /*return userRepository.findById(id)
                .map(userProfileMapper::map)
                .orElseThrow(() -> new ResourceNotFoundException("user:" + id));

         */
        Profile profile = userRepository.findProfileById(id).orElseThrow(() -> new ResourceNotFoundException(""));
        System.out.println(profile.gender());
        return profile;
    }

    public Profile createNewUser(User user) {
        Role role = new Role();
        role.setId(roleRepository.getDefaultRoleId());
        user.setRole(role);
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new NotAllowedException(ex.getMessage());
        }
        return userProfileMapper.map(user);
    }

    public Profile getAuthenticatedUserProfile() {
        long id = authService.getAuthenticatedUserId();
        return null;
    }
}
