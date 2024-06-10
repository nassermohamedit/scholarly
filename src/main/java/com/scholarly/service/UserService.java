package com.scholarly.service;

import com.scholarly.dto.PasswordUpdate;
import com.scholarly.dto.Profile;
import com.scholarly.entity.Role;
import com.scholarly.entity.User;
import com.scholarly.exceptions.UnauthorizedException;
import com.scholarly.exceptions.ResourceNotFoundException;
import com.scholarly.repository.*;
import com.scholarly.security.AuthenticationService;
import com.scholarly.utils.UserProfileMapper;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserProfileMapper userProfileMapper;

    private final AuthenticationService authService;

    private final PasswordEncoder passwordEncoder;

    public Profile getProfileById(long id) {
        Profile profile = userRepository.findProfileById(id).orElseThrow(() -> new ResourceNotFoundException(""));
        System.out.println(profile.gender());
        return profile;
    }

    public Profile createNewUser(User user) {
        Role role = new Role();
        role.setId((short) 1); // TODO - use role name instead
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new UnauthorizedException(ex.getMessage());
        }
        return userProfileMapper.map(user);
    }

    public List<Profile> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userProfileMapper::map)
                .toList();
    }

    public Profile updateUser(Long id, Profile data) {
        checkIfUserAuthenticated(id, "Not authorized");
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user:" + id));
        user.setLastname(data.lastname());
        user.setFirstname(data.firstname());
        user.setUsername(data.username());
        user.setCountry(data.country());
        user.setGender(data.gender());
        user.setEmail(data.email());
        userRepository.save(user);
        return userProfileMapper.map(user);
    }


    public void deleteUser(long id) {
        checkIfUserAuthenticated(id, "Not Authorized");
        // TODO - block future request processing
        userRepository.deleteById(id);
    }

    public void updatePassword(Long id, PasswordUpdate password) {
        checkIfUserAuthenticated(id, "");
        String oldPassword = userRepository.findPasswordById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user:" + id));
        if (!passwordEncoder.matches(password.oldPassword(), oldPassword)) {
            throw new UnauthorizedException("Incorrect password");
        }
        String encodedNewPassword = passwordEncoder.encode(password.newPassword());
        userRepository.updatePassword(id, encodedNewPassword);
    }

    private void checkIfUserAuthenticated(Long id, String message) {
        if (id != authService.getAuthenticatedUserId()) {
            throw new UnauthorizedException(message);
        }
    }


    public List<Profile> search(String like, int page, int size) {
        PageRequest pr = PageRequest.of(page, size);
        return userRepository.findByUsernameContaining(like, pr).stream()
                .map(userProfileMapper::map)
                .toList();
    }

}