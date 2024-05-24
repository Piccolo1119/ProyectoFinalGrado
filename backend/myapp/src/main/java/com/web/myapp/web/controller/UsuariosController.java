package com.web.myapp.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UsuariosController {

    @PostMapping(value = "usuarios")
    public String welcome() {
        return "welcome from public endpoint";
    }
}
