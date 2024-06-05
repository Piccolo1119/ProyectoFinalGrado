package com.web.myapp.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.myapp.jwt.JwtService;
import com.web.myapp.model.Rol;
import com.web.myapp.model.Usuarios;
import com.web.myapp.repository.UsuariosRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuariosRepository usuariosrepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPass()));
        UserDetails userDetails = usuariosrepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(userDetails);
        return AuthResponse.builder()
            .token(token)
            .build();
    }

    public AuthResponse register(RegisterRequest request) {
        
        Usuarios usuarios = Usuarios.builder()
            .username(request.getUsername())
            .pass(passwordEncoder.encode(request.getPass()))
            .email(request.getEmail())
            .nombre(request.getNombre())
            .apellidos(request.getApellidos())
            .telefono(request.getTelefono())
            .fechaNac(request.getFechaNac())
            .rol(Rol.USER)
            .build();

        usuariosrepository.save(usuarios);

        return AuthResponse.builder()
            .token(jwtService.getToken(usuarios))
            .build();
    }
}
