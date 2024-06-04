package com.scholarly.controller;


import com.scholarly.dto.Profile;
import com.scholarly.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user", produces = "application/json")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public Profile getUserById(@PathVariable long id) {
        return userService.getProfileById(id);
    }

}
