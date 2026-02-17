package com.example.rentaherramientas.services;

import com.example.rentaherramientas.models.User;
import com.example.rentaherramientas.repositories.UserRepository; // <--- ESTA ES LA LÍNEA 4
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // <--- ESTA ES LA LÍNEA 14

    public List<User> listarTodos() {
        return userRepository.findAll();
    }

    public User guardar(User user) {
        return userRepository.save(user);
    }
}