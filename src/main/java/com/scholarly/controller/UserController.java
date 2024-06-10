package com.scholarly.controller;


import com.scholarly.dto.Profile;
import com.scholarly.entity.User;
import com.scholarly.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user", produces = "application/json")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public Profile getUserById(@PathVariable long id) {
        return userService.getProfileById(id);
    }

    @PostMapping
    public Profile createNewUser(@Validated @RequestBody User user) {
        return userService
                .createNewUser(user);
    }

    @PutMapping("/{id}")
    public Profile updateUser(@RequestParam long id, @Validated @RequestBody Profile data) {
        return userService.updateUser(id, data);
    }

}
