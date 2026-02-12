package com.example.rentaherramientas.controllers;

import com.example.rentaherramientas.models.User;
import com.example.rentaherramientas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.listarTodos();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.guardar(user);
    }
}