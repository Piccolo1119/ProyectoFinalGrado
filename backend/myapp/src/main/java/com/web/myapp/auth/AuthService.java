package com.web.myapp.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.myapp.ExceptionHandler.UserAlreadyExistsException;
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
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println(request);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPass()));
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        UserDetails userDetails = usuariosrepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(userDetails);
        AuthResponse authResponse = new AuthResponse(token);
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println(authResponse);
        return authResponse;
        /* return AuthResponse.builder()
            .token(token)
            .build(); */
    }

    public AuthResponse register(RegisterRequest request) {
        // Check if user already exists
        if (usuariosrepository.findByUsername(request.getUsername()).isPresent() || usuariosrepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Username or email already registered");
        }

        Usuarios usuarios = Usuarios.builder()
            .nombre(request.getNombre())
            .apellidos(request.getApellidos())
            .email(request.getEmail())
            .username(request.getUsername())
            .pass(passwordEncoder.encode(request.getPass()))
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
