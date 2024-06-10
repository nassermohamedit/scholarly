package com.scholarly.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.scholarly.dto.Profile;
import com.scholarly.entity.Gender;
import com.scholarly.entity.User;
import com.scholarly.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private static final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void testGetUserById() throws Exception {
        long id = 1L;
        Profile profile = new Profile(1, "bestofall", "Paul", "Morphy", "genuis@a.com", Gender.MALE, "US");

        when(userService.getProfileById(anyLong())).thenReturn(profile);

        this.mockMvc.perform(get("/api/user/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(objectMapper.writeValueAsString(profile)));
    }

    @Test
    void testCreateNewUser() throws Exception {
        User user = User.builder()
                .username("magicianofriga")
                .lastname("Tal")
                .firstname("Mikhail")
                .email("mikhailtal@fide.com")
                .password("1234")
                .country("Russia")
                .gender(Gender.MALE)
                .build();

        Profile profile = new Profile(
                1L, "magicianofriga", "Mikhail", "Tal", "mikhailtal@fide.com", Gender.MALE, "Russia"
        );

        when(userService.createNewUser(any(User.class))).thenReturn(profile);

        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(profile)));
    }
}
