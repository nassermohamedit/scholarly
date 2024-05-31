package com.scholarly.endpoint;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class WelcomeController {

    @GetMapping
    public String welcome() {
        return "Welcome to Scholarly";
    }
}
