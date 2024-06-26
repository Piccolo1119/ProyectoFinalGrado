package com.web.myapp.service;

import org.springframework.stereotype.Service;

import com.web.myapp.auth.UsuariosRequest;
import com.web.myapp.auth.UsuariosResponse;
import com.web.myapp.mapper.impl.UsuariosDto;
import com.web.myapp.model.Rol;
import com.web.myapp.model.Usuarios;
import com.web.myapp.repository.UsuariosRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuariosService {
    private final UsuariosRepository usuariosRepository; 

    @Transactional
    public UsuariosResponse updateUsuarios(UsuariosRequest usuariosRequest) {
       
        Usuarios usuarios = Usuarios.builder()
        .id(usuariosRequest.id)
        .nombre(usuariosRequest.getNombre())
        .apellidos(usuariosRequest.getApellidos())
        .email(usuariosRequest.getEmail())
        .username(usuariosRequest.getUsername())
        .telefono(usuariosRequest.getTelefono())
        .fechaNac(usuariosRequest.getFechaNac())
        .rol(Rol.USER)
        .build();
        
        usuariosRepository.updateUsuarios(usuarios.getId(), usuarios.getNombre(), usuarios.getApellidos(), usuarios.getEmail(), usuarios.getUsername(), usuarios.getTelefono(), usuarios.getFechaNac());

        return new UsuariosResponse("El usuario se registró satisfactoriamente");
    }

    public UsuariosDto getUsuariosByUsername(String username) {
        Usuarios usuarios = usuariosRepository.findByUsername(username).orElse(null);
        
        if (usuarios != null) {
            return UsuariosDto.builder()
                    .id(usuarios.getId())
                    .nombre(usuarios.getNombre())
                    .apellidos(usuarios.getApellidos())
                    .email(usuarios.getEmail())
                    .username(usuarios.getUsername())
                    .telefono(usuarios.getTelefono())
                    .fechaNac(usuarios.getFechaNac())
                    .build();
        }
        
        return null;
    }

    public UsuariosDto getUsuariosId(Long id) {
        Usuarios usuarios = usuariosRepository.findById(id).orElse(null);
        
        if (usuarios != null) {
            return UsuariosDto.builder()
                    .id(usuarios.getId())
                    .nombre(usuarios.getNombre())
                    .apellidos(usuarios.getApellidos())
                    .email(usuarios.getEmail())
                    .username(usuarios.getUsername())
                    .telefono(usuarios.getTelefono())
                    .fechaNac(usuarios.getFechaNac())
                    .build();
        }
        
        return null;
    }

    public Usuarios getUsuarios(UsuariosRequest usuariosRequest) {
        return usuariosRepository.findByUsername(usuariosRequest.getUsername()).orElse(null);
    }
}