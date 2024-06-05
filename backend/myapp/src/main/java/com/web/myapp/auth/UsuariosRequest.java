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
    Long id;
    String nombre;
    String apellidos;
    String email;
    String username;
    String pass;
    String telefono;
    Date fechaNac;
}
