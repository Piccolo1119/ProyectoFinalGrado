package com.web.myapp.auth;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuariosRequest {
    public Long id;
    String nombre;
    String apellidos;
    String email;
    String username;
    String telefono;
    Date fechaNac;
}
