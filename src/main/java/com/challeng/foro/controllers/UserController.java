package com.challeng.foro.controllers;

import com.challeng.foro.domain.User;
import com.challeng.foro.exceptions.BadParameterRequestException;
import com.challeng.foro.services.UserService;
import com.challeng.foro.services.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/new")
    public ResponseEntity<User> create(@Valid @RequestBody User user) {

        if (service.existsByEmail(user.getEmail())) throw new BadParameterRequestException("The email exists") ;

        service.create(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).body(user);
    }
}
