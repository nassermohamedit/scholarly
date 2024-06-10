package com.scholarly.service;

import com.scholarly.dto.Profile;
import com.scholarly.entity.Gender;
import com.scholarly.entity.User;
import com.scholarly.exceptions.ResourceNotFoundException;
import com.scholarly.repository.RoleRepository;
import com.scholarly.repository.UserRepository;
import com.scholarly.security.AuthenticationService;
import com.scholarly.utils.UserProfileMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserProfileMapper userProfileMapper;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private AuthenticationService authService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getProfileById_userExists_returnsProfile() {
        long id = 1L;
        Profile profile = new Profile(
                1L, "thetactician", "Alexander", "Alekhine", "ibeatcapa@a.com", Gender.MALE, "Russia"
        );
        when(userRepository.findProfileById(id)).thenReturn(Optional.of(profile));

        Profile actual = userService.getProfileById(id);

        assertEquals(profile, actual);
        verify(userRepository).findProfileById(id);
    }

    @Test
    void getProfileById_userDoesNotExist_throwsResourceNotFoundException() {
        long id = 1L;
        when(userRepository.findProfileById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.getProfileById(id));
        verify(userRepository).findProfileById(id);
    }

    @Test
    void createNewUser_validUser_returnsProfile() {
        User user = User.builder()
                .username("thetactician")
                .firstname("Alexander")
                .lastname("Alekhine")
                .email("ibeatcapa@a.com")
                .gender(Gender.MALE)
                .country("Russia")
                .password("ibeatcapablanca")
                .build();
        Profile profile = new Profile(
                1L, "thetactician", "Alexander", "Alekhine", "ibeatcapa@a.com", Gender.MALE, "Russia"
        );

        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);
        when(userProfileMapper.map(user)).thenReturn(profile);

        Profile actual = userService.createNewUser(user);

        assertEquals(profile, actual);
        verify(passwordEncoder).encode("ibeatcapablanca");
        verify(userRepository).save(user);
        verify(userProfileMapper).map(user);
    }

    @Test
    void createNewUser_dataIntegrityViolation_throwsNotAllowedException() {

    }

    @Test
    void getAuthenticatedUserProfile_authenticatedUser_returnsProfile() {
    }

}
