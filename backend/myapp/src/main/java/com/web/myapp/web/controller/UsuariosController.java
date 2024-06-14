package com.web.myapp.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.myapp.auth.UsuariosRequest;
import com.web.myapp.auth.UsuariosResponse;
import com.web.myapp.jwt.JwtService;
import com.web.myapp.mapper.impl.UsuariosDto;
import com.web.myapp.model.Usuarios;
import com.web.myapp.service.UsuariosService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class UsuariosController {
    private final UsuariosService usuariosService;
    private final JwtService jwtService;

    @GetMapping(value = "{id}")
    public ResponseEntity<UsuariosDto> getUsuariosId(@PathVariable Long id, @RequestHeader("Authorization") String token) 
    {
        String username = jwtService.getUsernameFromToken(token.substring(7));
        UsuariosDto usuariosDto = usuariosService.getUsuariosByUsername(username);
        if(usuariosDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuariosDto);
    }

    @PutMapping()
    public ResponseEntity<UsuariosResponse> updateUser(@RequestBody UsuariosRequest usuariosRequest)
    {
        return ResponseEntity.ok(usuariosService.updateUsuarios(usuariosRequest));
    }
    
    @GetMapping()
    public ResponseEntity<Usuarios> getUsuarios(@RequestBody UsuariosRequest usuariosRequest)
    {
        return ResponseEntity.ok(usuariosService.getUsuarios(usuariosRequest));
    }
}
