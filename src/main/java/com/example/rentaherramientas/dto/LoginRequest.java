package com.example.rentaherramientas.dto;

import lombok.Data; // Lombok nos ahorra escribir getters y setters

@Data
public class LoginRequest {
    private String username;
    private String password;
}