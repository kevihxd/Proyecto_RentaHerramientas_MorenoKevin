package com.example.rentaherramientas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
        return "forward:/index.html";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "forward:/register.html";
    }

    @GetMapping("/catalogo")
    public String catalogo() {
        return "forward:/panel-client.html";
    }
}